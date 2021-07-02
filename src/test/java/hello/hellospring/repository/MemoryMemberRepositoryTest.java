package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//전체 클래스에서 전체 테스트 가능
//전체 테스트를 돌리면 findByName에서 에러 발생
// 테스트 순서는 보장되지 않는다.
//finalAll이 먼저 실행
//findAll에서 이미 spring1을 저장 -> findByName에서 findAll에서 저장한 객체가 반환됨
//다른 객체라고 판정
//따라서 테스트가 끝나면 데이터를 크리어해야함

//테스트케이스는 서로 의존성이 없도록 설계해야함
// 따라서 저장소나 공용 데이터를 지워줘야 문제가 없다.

//테스트만 해도 깊은 내용이 있다.
//테스트 먼저 작성하고 memorymeber 나중에 작성할 수도 있다.
//미리 검증할 틀을 만들고 맞는지 확인
//->TDD

//테스트가 많은 경우 패키지에서 run
//테스트 케이스 없이 개발하기는 불가능, 문제가 많이 발생가능
//테스트 작성하는 것 꼭 익혀라
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach//매소드 실행 끝날 때마다 실행되는 매서드
    public void afterEach(){
        repository.clearStore();
    }

    @Test // JUnit이라는 프레임워크로 테스트 실행
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //잘 저장되었는지 검증
        //optinal 은 get으로 꺼낸다 그러나 좋은 방법은 아님 testcode에서 사용하는 건 ok
        Member result = repository.findById(member.getId()).get();

        //저장한 값과 db의 값이 같은가 검증
        //System.out.println("result = "+(result==member));

        //assert 사용
        //junit
        //Assertions.assertEquals(member, result);
        //기대값과 다른 경우 오류 발생

        //assertj 최근 사용하는 것 더 편하게 사용 가능
        assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public  void findAll(){
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
