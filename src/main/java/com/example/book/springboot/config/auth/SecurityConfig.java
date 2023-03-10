package com.example.book.springboot.config.auth;

import com.example.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // csrf() ~ disable(): h2-console 화면을 사용하기 위해 해당 옵션 disable
                .and()
                    .authorizeRequests() // URL 별 권한 관리를 설정하는 옵션의 시작점, authorizeRequest 가 선언 되어야만 antMatchers 옵션 사용 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // antMatchers: 권한 관리 대상 지정 옵션, URL HTTP 메서드별로 관리 가능
                    // "/"등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 줌
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // "/api/v1/**" 주소를 가진 API는 USER 권한을 가져야만 가능
                    .anyRequest().authenticated() // anyRequest: 설정된 값들 이외 나머지 URL, authenticated: 나머지 URL은 인증된 사용자 허용(로그인한 사용자)
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // logout().logoutSuccessUrl("/") 로그아웃 기능에 대한 설정의 진입점, 로그아웃 성공 시 "/" 주소로 이동
                .and()
                    .oauth2Login() // OAuth2 로그인 기능에 대한 설정의 진입점
                        .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정 담당
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
                            // 리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고 하는 기능 명시 가능
    }
}
