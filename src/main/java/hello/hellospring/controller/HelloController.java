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
        return "hello"; // resources/templates/"hello" 찾아서 렌더링
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    // 단축키: 커맨드 + P -> 파라미터 정보 볼 수 있다.
    // required true 이면 기본으로 값을 넘겨야 한다.
    // http://localhost:8080/hello-mvc?name=spring!!!!!! -> hello spring!!!!!! 출력

    @GetMapping("hello-string")
    @ResponseBody   // http 의 header, body 중 body 에 내용을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;  // "hello spring"
        // 소스 보기를 하면 html 태그 없이 문자열을 그대로 내려준다.
    }

    @GetMapping("hello-api")
    @ResponseBody   // 객체 반환하고 응답으로 지정하면 기본이 JSON 형식으로 반환 지정, xml 로 바꾸는 것도 가능하지만 비추
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // JSON 으로 반환 {"name":"spring!!!"}
    }

    static class Hello {
        private String name;

        // 자바 bean 규약
        // command + N > Getter and Setter 로 자동 생성
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
