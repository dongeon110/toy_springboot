package com.example.book.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); // assertj라는 라이브러리의 테스트 검증 메서드, 검증하고 싶은 대상을 메서드 인자로 받음, 메서드 체이닝이 지원되어 isEqual와 같이 메서드를 이어서 사용 가능
        assertThat(dto.getAmount()).isEqualTo(amount); // assertj의 동등 비교 메서드, assertThat에 있는 값과 isEqual의 값을 비교해서 같을 때만 성공
    }
}
