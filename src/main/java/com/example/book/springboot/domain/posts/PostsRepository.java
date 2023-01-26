package com.example.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> { // JpaRepository<Entity 클래스, PK 타입>
    // DB Layer 접근자 Dao, Repository (in JPA) 인터페이스로 생성
    // 기본적인 CRUD 자동 생성
    // Entity 클래스와 기본 Entity Repository는 함께 위치해야 할 것, Entity는 기본 Repository 없이는 제대로 역할을 할 수 가 없음

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // SpringDataJpa 에서 제공하지 않는 메서드는 쿼리로 작성해도 됨
    List<Posts> findAllDesc();
    // 데이터의 조회는 FK의 조인, 복잡한 조건 등으로 이런 Entity 만으로는 처리하기 어려워 조회용 프레임워크를 추가 사용함
    // 대표적으로 querydsl, jooq, MyBatis 등이 있음
    // 조회는 위 3개의 프레임워크 중 하나, 등록/수정/삭제는 SpringDataJpa 를 사용
    // querydsl 이 타입안정성 지원, 레퍼런스 등 좋은 점 많음
}
