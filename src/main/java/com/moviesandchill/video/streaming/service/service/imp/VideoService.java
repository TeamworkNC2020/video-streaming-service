package com.moviesandchill.video.streaming.service.service.imp;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
@EnableAsync
public class VideoService {

    private final String awsS3Logo = "https://logouser.s3-us-west-2.amazonaws.com/";

    private final String awsS3HLS = "https://moviescontainerhls.s3-us-west-2.amazonaws.com/";

    private String bucketLogoName = "logouser";

    private String bucketHLSName = "moviescontainerhls";

    private AmazonS3 s3client;

    @Value("${ffmpeg.ffmpeg_path}")
    private String ffmpegPath;

    @Value("${ffmpeg.ffprobe_path}")
    private String ffprobePath;

    @Value("${ffmpeg.input_directory}")
    private String inputDirectory;

    @Value("${ffmpeg.output_directory}")
    private String outputDirectory;

    public String updateUserLogo(long userId, MultipartFile file) throws IOException {
        String pathKey = userId + "/" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        s3client.putObject(new PutObjectRequest(bucketLogoName,
                pathKey,
                file.getInputStream(), metadata)
                .withAccessControlList(s3client.getBucketAcl(bucketLogoName)));

        return awsS3Logo + URLEncoder.encode(pathKey, StandardCharsets.UTF_8.toString());
    }

    public void uploadVideo(long filmId,MultipartFile file) throws IOException {
        uploadVideoAsync(filmId,file);
    }

    @Async
    public void uploadVideoAsync(long filmId,MultipartFile file) throws IOException {
        Files.createDirectory(Paths.get(inputDirectory + "\\" + filmId));
        Files.createDirectory(Paths.get(outputDirectory + "\\" + filmId));
        String inputDir = inputDirectory + "\\" + filmId + "\\input.mp4";
        String outputDir = outputDirectory + "\\" + filmId + "\\index.m3u8";
        File copied = new File(inputDir);
        try (
                InputStream in = new BufferedInputStream(
                        file.getInputStream());
                OutputStream out = new BufferedOutputStream(
                        new FileOutputStream(copied))) {

            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        }
        FFmpeg ffmpeg = new FFmpeg(ffmpegPath);
        FFprobe ffprobe = new FFprobe(ffprobePath);
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(inputDir)     // Filename, or a FFmpegProbeResult
                .overrideOutputFiles(true) // Override the output if it exists
                .addOutput(outputDir)   // Filename for the destination
                .setFormat("hls")            // Format is inferred from filename, or can be set
                .disableSubtitle()       // No subtiles
                .setAudioChannels(1)         // Mono audio
                .setAudioCodec("aac")        // using the aac codec
                .setAudioSampleRate(48_000)  // at 48KHz
                .setAudioBitRate(128000)          // at 128000 kbit/s
                .setVideoCodec("h264")     // Video using x264
                .setVideoFrameRate(60, 1)     // at 24 frames per second
                .setVideoResolution(1280, 720) // at 1280x720 resolution
                .addExtraArgs(new String[]{"-hls_playlist_type", "vod","-hls_time", "6"})
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        executor.createJob(builder).run();

        String resultManifest = "";
        File folder = new File(outputDirectory + "\\" + filmId);
        File[] listOfFiles = folder.listFiles();
        for (File fileHls : listOfFiles) {
            if (fileHls.isFile()) {
                String pathKey = filmId + "/" + fileHls.getName();
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(fileHls.length());
                s3client.putObject(new PutObjectRequest(bucketHLSName,
                        pathKey,
                        new FileInputStream(fileHls), metadata)
                        .withAccessControlList(s3client.getBucketAcl(bucketHLSName)));
                if(FilenameUtils.getExtension(fileHls.getName()).equals("m3u8")){
                    resultManifest = awsS3HLS + URLEncoder.encode(pathKey, StandardCharsets.UTF_8.toString());
                }
            }
        }
        System.out.print(resultManifest);
    }


    @Autowired
    public void setS3client(@Value("${endpoint.accessKey}") String accessKey, @Value("${endpoint.secretKey}") String secretKey) {
        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                        accessKey,
                        secretKey
                )))
                .withRegion(Regions.US_WEST_2)
                .build();
    }


}
