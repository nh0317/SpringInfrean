package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//단위 테스트 순수한 자바로 작성
//spring 없이 테스트하는 것에 익숙해야한다. (더 좋은 테스트일 수 있다.)
//좋은 단위 테스트를 만드는 것이 중요 (실행시간에 엄청난 차이가 남)
class MemberServiceTest {

    //new로 생성했기 때문에 다른 객체임
    //memberService와 다른 객체임
    //굳이 2개의 객체를 사용할 이유가 없음 같은 객체를 사용하는 것이 더 좋다.
    //혹시라도 내용물이 달라질 수 있음

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //테스트를 실행하기 전에
    //memoryMemberRepository를 생성해서 memberService에 넣어준다.
    //같은 memoryMemberRepository 사용
    //memberService 입장에서 객체를 직접 생성하지 않음
    //Dependency Injection
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService= new MemberService(memberRepository);
    }
    //여기서도 마찬가지로 clear가 필요함
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test // test는 한글로 적어도 괜찮다.
    //test는 given when then 단계로 설계
    //상황에 따라 점점 변형
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);

        //then
        //repository에서 꺼내서 찾는다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    //중복 회원로직 검증하는 것이 중요
    //예외 처리가 되는 가가 중요
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
//        try {
//            memberService.join(member2);//예외가 발생해야함
//            fail();
//        }catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }
        //try catch를 넣는 것이 애매함

        //람다식의 예외가 발생할 겻을 기대함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}