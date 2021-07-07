package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    // url 쳤을 때 매핑
    // 조회할 때 주로 사용
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 데이터를 form 같은 데 넣어 전달할 때 사용
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
    