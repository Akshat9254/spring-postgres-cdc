package com.spring.springpostgrescdc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;

public record CreateBlogRequest(
        @NotBlank(message = "Title must not be blank")
        String title,

        @NotNull(message = "Author ID must not be null")
        @Positive(message = "Author ID must be positive")
        Long authorId,

        @NotBlank(message = "Upload ID must not be blank")
        @UUID(message = "Invalid upload ID")
        String uploadId
) {
}
