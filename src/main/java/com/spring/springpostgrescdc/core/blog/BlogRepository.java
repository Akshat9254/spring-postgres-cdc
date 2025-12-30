package com.spring.springpostgrescdc.core.blog;

import org.springframework.data.jpa.repository.JpaRepository;

interface BlogRepository extends JpaRepository<Blog, Long> {
}
