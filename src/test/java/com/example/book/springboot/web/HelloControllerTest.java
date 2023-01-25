package com.example.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 테스트를 실행할 때, JUnit에 내장된 실행자 외에 다른 실행자 실행
// 여기서는 SpringRunner라는 스프링 실행자를 사용
// 스프링 부트 테스트와 JUnit 연결자 역할
@WebMvcTest(controllers = HelloController.class) // 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
// 선언시 @Controller, @ControllerAdvice 등을 사용할 수 있음
// 단, @Service, @Component, @Repository 등은 사용할 수 없음
// 여기서는 컨트롤러만 사용하기 때문에 선언
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; // 웹 API 테스트 할때 사용, 스프링 MVC 테스트의 시작점, 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청, 체이닝이 지원되어 아래처럼 여러 검증을 이어서 선언가능
                .andExpect(status().isOk()) // mvc.perform 결과 검증, HTTP Header Status 검증, Ok = 200 인지 아닌지
                .andExpect(content().string(hello)); // mvc.perform 결과 검증, String hello를 리턴하기 때문에 이 값이 맞는 지 검증
    }
}
