package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")//첫 webpage 방문시 실행
    public String home() {
        return "home";
    }
}

//home 화면도 이전과 동일하게 우선 관련 controller가 있는지 찾는다.
//welcome page도 controller가 있는지 찾음
// '/'이 home.html과 매핑되어 index.html은 무시되고 home.html이 실행된다
