package com.example.book.springboot.web;

import com.example.book.springboot.config.auth.LoginUser;
import com.example.book.springboot.config.auth.dto.SessionUser;
import com.example.book.springboot.service.posts.PostsService;
import com.example.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // @LoginUser SessionUser user: 기존 아래 httpSession.getAttibute("user")로 가져오던 세션 정보 값이 개선 됨
        // 어느 컨트롤러든 @LoginUser 만 사용하면 세션정보를 가져 올 수 있게 되었음

        model.addAttribute("posts", postsService.findAllDesc()); // 서버 템플릿 엔진(현재 mustache)에서 사용할 수 있는 객체를 저장
        // 여기서는 postsService.findAllDesc()로 가져온 결과를 posts 로 index.mustache 에 전달

        // SessionUser user = (SessionUser)httpSession.getAttribute("user"); // CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser 저장
        // 어노테이션으로 개선

        if (user != null) { // 세션에 저장된 값이 있으면 model에 userName으로 등록
            model.addAttribute("userName", user.getName());
        }

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
