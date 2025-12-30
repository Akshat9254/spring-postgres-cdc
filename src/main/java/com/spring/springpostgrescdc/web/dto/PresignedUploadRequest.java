package com.spring.springpostgrescdc.web.dto;

import com.spring.springpostgrescdc.web.validation.ValidContentType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PresignedUploadRequest(
        @NotBlank(message = "Content Type must not be blank")
        @ValidContentType(message = "Content Type must be text/markdown")
        String contentType,

        @Min(value = 1, message = "File Size must be greater than or equal to 1B")
        @Max(value = 1048576, message = "File Size must be less than or equal to 1MB")
        long maxSizeBytes
) {
}
