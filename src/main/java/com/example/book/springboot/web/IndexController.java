package com.example.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index"; // mustache starter 덕분에 앞의 경로는 src/main/resources/templates, 뒤로는 .mustache 가 붙음
        // 해당 경로는 src/main/resources/templates/index.mustache 로 전환되어 View Resolver 가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
