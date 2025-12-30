package com.spring.springpostgrescdc.web.dto;

public record CreateBlogResponse(
        Long blogId,
        String contentPath,
        int version
) {
}
