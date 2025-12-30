package com.spring.springpostgrescdc.core.blog;

import com.spring.springpostgrescdc.web.dto.PresignedUploadRequest;
import com.spring.springpostgrescdc.web.dto.PresignedUploadResponse;
import com.spring.springpostgrescdc.web.exception.PresignedUrlGenerationException;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadService {
    private static final int URL_TTL_SECONDS = 600;
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    public PresignedUploadResponse createPresignedUpload(
            PresignedUploadRequest request
    ) {
        String uploadId = UUID.randomUUID().toString();
        String objectKey = "temp/" + uploadId + ".md";
        Map<String, String> headers = Map.of(
                "Content-Type", request.contentType()
        );

        try {
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(bucket)
                            .object(objectKey)
                            .expiry(URL_TTL_SECONDS)
                            .extraHeaders(headers)
                            .build()
            );

            return new PresignedUploadResponse(
                    uploadId,
                    objectKey,
                    url,
                    Instant.now().plusSeconds(URL_TTL_SECONDS)
            );
        } catch (ErrorResponseException e) {
            String code = e.errorResponse().code();

            // 🚨 Configuration / infra errors
            if ("NoSuchBucket".equals(code)) {
                throw new PresignedUrlGenerationException(
                        "Upload bucket does not exist", e
                );
            }
            if ("AccessDenied".equals(code)) {
                throw new PresignedUrlGenerationException(
                        "Access denied to object storage", e
                );
            }

            throw new PresignedUrlGenerationException(
                    "Failed to generate presigned upload URL", e
            );

        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            // 🔐 Signing / crypto failures
            throw new PresignedUrlGenerationException(
                    "Failed to sign presigned upload URL", e
            );

        } catch (Exception e) {
            // 🧯 IO / network / unexpected
            throw new PresignedUrlGenerationException(
                    "Unexpected error while generating upload URL", e
            );
        }
    }
}
