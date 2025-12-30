package com.spring.springpostgrescdc.core.blog;

import com.spring.springpostgrescdc.web.exception.BlogStorageException;
import com.spring.springpostgrescdc.web.exception.TempUploadNotFoundException;
import io.minio.CopyObjectArgs;
import io.minio.CopySource;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinioBlogStorageService {
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    public String moveFromTemp(String uploadId, Long blogId, int version) {
        String source = "temp/" + uploadId + ".md";
        String target = blogId + "/v" + version + ".md";

        try {
            minioClient.copyObject(
                CopyObjectArgs.builder()
                    .bucket(bucket)
                    .object(target)
                    .source(
                        CopySource.builder()
                            .bucket(bucket)
                            .object(source)
                            .build()
                    )
                    .build()
            );

            // Delete temp
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(source)
                    .build()
            );

            return target;
        } catch (ErrorResponseException e) {
            // 🔴 Source object missing
            if ("NoSuchKey".equals(e.errorResponse().code())) {
                throw new TempUploadNotFoundException(uploadId);
            }
            throw new BlogStorageException("MinIO error while moving blog content", e);

        } catch (Exception e) {
            // Everything else: IO, crypto, parsing, internal MinIO errors
            throw new BlogStorageException("Failed to move blog content", e);
        }
    }
}
