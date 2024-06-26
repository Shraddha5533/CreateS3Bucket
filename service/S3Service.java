package com.creates3bucket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.BucketAlreadyExistsException;
import software.amazon.awssdk.services.s3.model.BucketAlreadyOwnedByYouException;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Autowired
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Value("${application.bucket.name}")
    private String bucketName;

    public void createBucket(String bucketName) {
        try {
            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build();

            CreateBucketResponse createBucketResponse = s3Client.createBucket(createBucketRequest);
            System.out.println("Bucket created: " + createBucketResponse.location());
        } catch (BucketAlreadyExistsException | BucketAlreadyOwnedByYouException e) {
            System.out.println(e.awsErrorDetails().errorMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
