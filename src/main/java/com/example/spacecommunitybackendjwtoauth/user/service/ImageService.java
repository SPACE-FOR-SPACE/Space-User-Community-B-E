package com.example.spacecommunitybackendjwtoauth.user.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3 amazonS3;

    @Value("${spring.cloud.aws.s3.bukkit}")
    private String bucketName;

    // S3에 Image Upload 후 URL 보내주는 함수
    public String uploadImage(MultipartFile image) throws IOException {
        // Image 경로 설정 UUID_원본이름으로 유일하게 설정해줌
        String imagename = UUID.randomUUID() + "_"+ image.getOriginalFilename();

        // Image의 부가적인 정보들을 metadata에 저장.
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(image.getContentType());
        metadata.setContentLength(image.getSize());

        // PutObjectRequest 객체를 생성하여, 어디 버킷에 어떤 이름으로, 어떤 정보와 부가정보를 넣을 것인지가 적혀져 있음
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, imagename, image.getInputStream(), metadata);

        // 그 객체를 실제로 S3에 업로드함
        amazonS3.putObject(putObjectRequest);

        // 업로드한 객체의 URL을 가져옴.
        return getPublicURL(imagename);
    }

    private String getPublicURL(String filename) {
        // filename 인 이미지 정보가 있는 URL을 리턴
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, amazonS3.getRegion(), filename);
    }
}
