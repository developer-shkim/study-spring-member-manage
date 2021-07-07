package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    // 객체를 new 하면 인스턴스를 생성하는데, 별 기능이 없으므로
    // 컨테이너에 등록해서 공용으로 사용하는 것이 효율적이다.
//    private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    @Autowired  // 스프링이 주입해준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
