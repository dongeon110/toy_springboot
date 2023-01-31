package com.example.book.springboot.web;

import com.example.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // 테스트를 실행할 때, JUnit에 내장된 실행자 외에 다른 실행자 실행
// 여기서는 SpringRunner라는 스프링 실행자를 사용
// 스프링 부트 테스트와 JUnit 연결자 역할
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = { // WebMvcTest는 @Repository, @Service, @Component는 스캔대상이 아님, 따라서 SecurityConfig는 읽지만, CustomOAuth2UserService는 읽지 않아서 오류 발생
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        })
// 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
// 선언시 @Controller, @ControllerAdvice 등을 사용할 수 있음
// 단, @Service, @Component, @Repository 등은 사용할 수 없음
// 여기서는 컨트롤러만 사용하기 때문에 선언
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; // 웹 API 테스트 할때 사용, 스프링 MVC 테스트의 시작점, 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 가능

    /**
     * USER 롤이 있는 경우 성공. hello를 리턴해야 한다.
     * @throws Exception
     */
    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청, 체이닝이 지원되어 아래처럼 여러 검증을 이어서 선언가능
                .andExpect(status().isOk()) // mvc.perform 결과 검증, HTTP Header Status 검증, Ok = 200 인지 아닌지
                .andExpect(content().string(hello)); // mvc.perform 결과 검증, String hello를 리턴하기 때문에 이 값이 맞는 지 검증
    }

    /**
     * 권한이 없는 경우 302 에러를 리턴해야 한다.
     * @throws Exception
     */
    @Test
    public void hello가_리턴된다_fail() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().is(302));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) // param: API 테스트할 때 사용될 요청 파라미터 설정 (단, 값은 String만)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonPath: JSON 응답값을 필드별로 검증 할 수 있는 메서드, $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
