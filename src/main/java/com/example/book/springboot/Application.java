package com.example.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 활성화 // 테스트시 같이 스캔하게 되어 분리하기 위해 비활성화 JpaConfig로 이동
@SpringBootApplication // Springboot의 자동 설정, 스프링 Bean 읽기와 생성 모두 자동
// @SpringBootApplication 이 있는 위치부터 설정을 읽어가기 때문에 항상 프로젝트 최상단 위치
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // Springboot 내장 WAS 실행
    }
}
