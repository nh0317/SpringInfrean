package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // spring test, DB까지 테스트

//DB는 commit을 해야 DB에 반영됨(transaction)(VS Auto commit)
//test 끝나면 rollback하면 그 데이터를 없앨 수 있음
//transactional 어노테이션 사용
//test 끝나면 rollback해서 반복적으로 테스트 가능
@Transactional
class MemberServiceIntegrationTest {

    //스프링에게 객체를 달라고 해야함
    //Autowired 이용
    // DB를 지우고 해야 정확한 테스트 가능
    // 보통 test용 DB를 사용한다.
    @Autowired // test 할때는 필드 기반으로 Autowired 해도 된다. (다른데서 쓸게 아니므로)
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    //transactional 없이 회원가입을 반복하여 테스트하면 에러발생
    //DB에 값이 남아있기 때문
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);

        //then
        //repository에서 꺼내서 찾는다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        //then
    }
}