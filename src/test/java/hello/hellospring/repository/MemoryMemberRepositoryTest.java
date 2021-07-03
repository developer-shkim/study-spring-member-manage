package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//public class MemoryMemberRepositoryTest { // 다른 데에서 가져다 쓰지 않으므로 public 하지 않아도 된다.
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();    // 테스트가 실행되고 끝날 때마다 store 를 지운다. -> 각 케이스 실행이 순서에 의존적이지 않게 된다.
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

//        Assertions.assertEquals(member, result);  // org.junit.jupiter.api.Assertions
        assertThat(member).isEqualTo(result);       // org.assertj.core.api.Assertions.*
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

        /*
        System.out.println(repository.findByName("spring1"));
        // get X
        // Optional[hello.hellospring.domain.Member@330104b1]

        System.out.println(result);
        // get O
        // hello.hellospring.domain.Member@de0fbf3
         */
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
