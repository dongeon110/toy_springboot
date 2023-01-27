package com.example.book.springboot.web;

import com.example.book.springboot.service.posts.PostsService;
import com.example.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("posts", postsService.findAllDesc()); // 서버 템플릿 엔진(현재 mustache)에서 사용할 수 있는 객체를 저장
        // 여기서는 postsService.findAllDesc()로 가져온 결과를 posts 로 index.mustache 에 전달

        return "index"; // mustache starter 덕분에 앞의 경로는 src/main/resources/templates, 뒤로는 .mustache 가 붙음
        // 해당 경로는 src/main/resources/templates/index.mustache 로 전환되어 View Resolver 가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
