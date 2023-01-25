package com.example.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { // JpaRepositry<Entity 클래스, PK 타입>
    // DB Layer 접근자 Dao, Repository (in JPA) 인터페이스로 생성
    // 기본적인 CRUD 자동 생성
    // Entity 클래스와 기본 Entity Repository는 함께 위치해야 할 것, Entity는 기본 Repository 없이는 제대로 역할을 할 수 가 없음
}
