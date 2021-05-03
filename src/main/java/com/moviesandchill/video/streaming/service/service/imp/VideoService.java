package com.moviesandchill.video.streaming.service.service.imp;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class VideoService {

    private final String awsS3 = "https://logouser.s3-us-west-2.amazonaws.com/";

    private String bucketName = "logouser";

    private AmazonS3 s3client;

    public String updateUserLogo(long userId, MultipartFile file) throws IOException {
        String pathKey = userId + "/" + file.getName();
        s3client.putObject(new PutObjectRequest(bucketName,
                pathKey,
                file.getInputStream(),new ObjectMetadata())
                .withAccessControlList(s3client.getBucketAcl(bucketName)));;
        return awsS3 + bucketName + "/" + pathKey;
    }

    @Autowired
    public void setS3client(@Value("${endpoint.accessKey}") String accessKey,@Value("${endpoint.secretKey}") String secretKey) {
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
