package com.example.book.springboot.web;

import com.example.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON으로 반환하는 컨트롤러로 만들어 줌
// 예전에는 @ResponseBody를 각 메서드 마다 선언했던 것을 한번에 사용할 수 있게 해줌
public class HelloController {
    
    @GetMapping("/hello") // GET 요청을 받을 수 있는 API를 만들어 줌
    // 예전에는 @RequestMapping(method = RequestMethod.GET)으로 사용됨
    // 이제 이 프로젝트는 /hello로 요청이 오면 문자열 hello를 반환하는 기능을 가지게 됨
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, // @RequestParam 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션, name(@RequestParam)이라는 이름으로 저장한 파라미터를 name(String name)에 저장
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
