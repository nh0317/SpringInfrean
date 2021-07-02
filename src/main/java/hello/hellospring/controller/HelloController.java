package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";//같은 이름의 템플릿에 반환
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    public String helloString(@RequestParam("name") String name){
        return"hello"+name;
     }
     //html 태그 없이 적은 문자가 그대로 들어감 그대로 데이터를 내려주는 방식
     // html 태그를 붙여서 작성할 순 있지만 굳이 그렇게 하지 않는다.

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    //{"name":"spring!!!"} json 방식 key value로 이루어진 구조
    //xml 방식 <html></html>은 무겁고 열고 닫고 태그를 2번 사용해야함
    //반면 json은 simple함
    //최근에는 json 방식으로 통합함
    //기본은 json 방식


    //api 방식
    //responsebody가 있으면 http 응답에 그대로 넘김
    //객제가 아닌 문자이면 바로 반환
    //객체면 json 방식으로 데이터를 만들어서 http 응답에 반환하겠다.
    //단순 문자이면 string converter동작
    //객체면 jsonConverter 동작

    // 문자는 StringHttpMessageConverter 동작
    // 객체는 MappingJacksonH2HttpMessageConverter 동작(json 형식으로 변환)
    //(xml 등 특정 converter로 변환 가능)


    static class Hello{
        private String name;

        //java bin 규약
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
