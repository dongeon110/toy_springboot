package com.example.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 이 어노테이션이 생성될 수 있는 위치, PARAMETER: 메서드의 파라미터로 선언된 객체만이 사용할 수 있음
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
