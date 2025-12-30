package com.spring.springpostgrescdc.web.exception;

import com.spring.springpostgrescdc.web.dto.ApiResponse;
import com.spring.springpostgrescdc.web.dto.RequestFieldError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ApiResponse<List<RequestFieldError>> handleInvalidArgument(MethodArgumentNotValidException ex) {
        List<RequestFieldError> requestFieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(RequestFieldError::fromFieldError)
                .toList();

        return new ApiResponse<>(false, "Validation failed", requestFieldErrors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TempUploadNotFoundException.class)
    ApiResponse<Void> handleTempUploadNotFound(TempUploadNotFoundException ex) {
        return new ApiResponse<>(false, ex.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BlogStorageException.class)
    ApiResponse<Void> handleBlogStorageException(BlogStorageException ex) {
        log.error("Error while moving blog content", ex);
        return new ApiResponse<>(false, ex.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidUploadRequestException.class)
    ApiResponse<String> handleInvalidUpload(InvalidUploadRequestException ex) {
        return new ApiResponse<>(false, ex.getMessage(), "INVALID_UPLOAD_REQUEST");
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(PresignedUrlGenerationException.class)
    public ApiResponse<String> handlePresignedUrlError(PresignedUrlGenerationException ex) {
        return new ApiResponse<>(false, "Unable to generate upload URL", "UPLOAD_SERVICE_UNAVAILABLE");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    ApiResponse<Void> handleException(Exception ex) {
        log.error("Unexpected error", ex);
        return new ApiResponse<>(false, "Something went wrong", null);
    }
}
