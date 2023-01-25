package com.example.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 초기 구축 단계에서 테이블 설계가 빈번하게 변경되는데 lombok은 코드변경량을 최소화 해줌
@NoArgsConstructor // 기본 생성자 자동 추가 (public Posts() {} 와 같은 효과)
@Entity // 테이블과 링크될 클래스임을 나타냄, 기본값으로 카멜케이스 -> 언더스코어 네이밍으로 테이블이름을 매칭함
public class Posts {

    @Id // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙, GenerationType.IDENTITY = auto_increment, 웬만하면 Entity의 PK는 Long타입의 Auto_increment 추천 (MySQL 기준 bigint)
    // 하지않을 경우, 인덱스에 좋지 않음, FK 맺을때 중간 테이블을 둬야하는 상황 발생, 유니크한 조건 변경시 PK 전체를 수정해야하는 일 발생
    private Long id;

    @Column(length = 500, nullable = false) // 선언 안해도 해당 클래스의 필드는 모두 컬럼, 기본값 외 추가로 변경이 필요한 옵션, varchar기본은 255인데 500으로 늘림, 타입 변경 등
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Entity 클래스에서는 절대로 Setter 메서드를 만들지 말 것
}
