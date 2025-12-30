package com.spring.springpostgrescdc.web.dto;

import java.time.Instant;

public record PresignedUploadResponse(
        String uploadId,
        String objectKey,
        String presignedUrl,
        Instant expiresAt
) {
}
