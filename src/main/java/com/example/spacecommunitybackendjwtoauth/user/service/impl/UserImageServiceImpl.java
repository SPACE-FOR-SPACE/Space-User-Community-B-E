package com.example.spacecommunitybackendjwtoauth.user.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.spacecommunitybackendjwtoauth.user.service.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserImageServiceImpl implements UserImageService {
    private final AmazonS3 amazonS3;

    @Value("${spring.cloud.aws.s3.bukkit}")
    private String bucketName;

    @Override
    public String getUserProfileImageUrl(MultipartFile image) throws IOException{
        String imageName = getImageName(image);

        ObjectMetadata metadata = getObjectMetadata(image);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, imageName, image.getInputStream(), metadata);

        amazonS3.putObject(putObjectRequest);

        return getPublicURL(imageName);
    }

    private String getImageName(MultipartFile image){
        return UUID.randomUUID() + "_" + image.getOriginalFilename();
    }

    private ObjectMetadata getObjectMetadata(MultipartFile image) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(image.getContentType());
        metadata.setContentLength(image.getSize());
        return metadata;
    }

    private String getPublicURL(String filename) {
        // filename 인 이미지 정보가 있는 URL을 리턴
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, amazonS3.getRegion(), filename);
    }
}
