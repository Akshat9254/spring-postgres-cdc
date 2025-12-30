package com.spring.springpostgrescdc.core.blog;

import com.spring.springpostgrescdc.web.dto.CreateBlogRequest;
import com.spring.springpostgrescdc.web.dto.CreateBlogResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogService {
    private final BlogRepository blogRepository;
    private final MinioBlogStorageService storageService;

    @Transactional
    public CreateBlogResponse createBlog(CreateBlogRequest request) {
        Blog blog = new Blog();
        blog.setTitle(request.title());
        blog.setAuthorId(request.authorId());
        blog.setStatus("PUBLISHED");
        blog.setVersion(1);
        blog.setContentPath("PENDING");

        blog = blogRepository.save(blog);

        String finalPath = storageService.moveFromTemp(
                request.uploadId(),
                blog.getId(),
                blog.getVersion()
        );

        blog.setContentPath(finalPath);
        blogRepository.save(blog);

        return new CreateBlogResponse(
                blog.getId(),
                finalPath,
                blog.getVersion()
        );
    }
}
