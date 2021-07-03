package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// command + shift + T => create new test
public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 외부에서 주입하도록 변경 = DI(Dependency Injection)
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 이름이 같은 회원 중복 X
        // Optional 이라서 가능, null 이 아니면

        // option + command + v => 표현식으로 변환
//        Optional<Member> byName = memberRepository.findByName(member.getName());

        // control + t => 리팩토링
        validateDuplicateMember(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        memberRepository.save(member);
    }

    /**
     * 전체 회원 조회
     */
    // 서비스 클래스는 비즈니스 로직과 매핑되도록
    // 리포지토리는 개발자스럽게 용어
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
