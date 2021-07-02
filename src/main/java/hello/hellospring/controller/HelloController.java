package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
