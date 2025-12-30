package com.spring.springpostgrescdc.web.controller;

import com.spring.springpostgrescdc.web.dto.ApiResponse;
import com.spring.springpostgrescdc.web.dto.CreateBlogRequest;
import com.spring.springpostgrescdc.web.dto.CreateBlogResponse;
import com.spring.springpostgrescdc.web.dto.PresignedUploadRequest;
import com.spring.springpostgrescdc.web.dto.PresignedUploadResponse;
import com.spring.springpostgrescdc.core.blog.BlogService;
import com.spring.springpostgrescdc.core.blog.UploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
@Slf4j
class BlogController {
    private final BlogService blogService;
    private final UploadService uploadService;

    @PostMapping("/presigned")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PresignedUploadResponse> getPresignedUrl(@Valid @RequestBody PresignedUploadRequest request) throws Exception {
        PresignedUploadResponse response = uploadService.createPresignedUpload(request);
        return new ApiResponse<>(true, "Presigned url generated", response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<CreateBlogResponse> createBlog(@Valid @RequestBody CreateBlogRequest request) {
        CreateBlogResponse response = blogService.createBlog(request);
        return new ApiResponse<>(true, "Blog created successfully", response);
    }
}
