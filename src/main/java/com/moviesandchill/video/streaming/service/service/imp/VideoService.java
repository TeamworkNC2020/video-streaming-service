package com.moviesandchill.video.streaming.service.service.imp;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    private final AWSCredentials credentials = new BasicAWSCredentials(
            "AKIAQUHWL6SCFQNE5KG2",
            "GMAXwTfAr5C9pAJ9hDJM5FNFr0FwGINoC/gHXgmw"
    );
    private final AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_WEST_2)
            .build();

    public S3Object getMovieVideo(String bucketName,String nameVideo) {
        S3Object s3object = s3client.getObject(bucketName, nameVideo);
        return s3object;
    }

}
