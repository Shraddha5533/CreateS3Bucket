package com.creates3bucket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class S3BucketCreator implements CommandLineRunner {

    private final S3Service s3Service;

    @Autowired
    public S3BucketCreator(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @Override
    public void run(String... args) {
        String bucketName = "my-unique-s3-bucket-2024"; // Replace with your bucket name
        s3Service.createBucket(bucketName);
    }
}
