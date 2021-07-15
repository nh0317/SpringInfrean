package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


//aop가 필요한 상황?
//모든 메소드의 호출 시간을 알고 싶을 떄
//근데 이는 시간을 핵심 관심 사항이 아님 공통 관심 사항임
//나중에 유지 보수가 어려움
//별도의 공통 로직으로 만들기 어렵다

//AOP
//공통 관심 사항과 핵심 관심사항을 분리
// 시간 측정 로직을 등록하고 연결함


//ctrl shift T로 테스트 자동 생성 가능
//@Service // spring 이 service라고 container에 등록

@Transactional // jpa를 사용할 경우 데이터를 저장하고 변경하는 경우 transaction안에서 실행해야한다.
public class MemberService {
    private  final MemberRepository memberRepository;

    //외부에서 넣어 주도록 변경
    //테스트에서도 동일한 객체 사용가능
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public  Long join(Member member){

//        같은 이름이 있는 중복 회원 X
//         Optional<Member> result = memberRepository.findByName(member.getName());

//        result.ifPresent(m->{
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });
//         optional로 감싼 덕에 ifPresent 등의 매서드 사용가능
//        get으로 바로 꺼내는 것을 권장하지 않는다
//        orElseGet : 있으면 꺼내고 없으면 매서드 실행 또는 default값을 넣어서 꺼냄
//        Optional을 바로 사용하는 것을 권장하지 않음

        //바로 ifPresent 사용
        //findByName으로 어떤 로직이 있음 매소드로 뽑는 걸 권장
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    //보통 service쪽은 비즈니스 의존적으로 설계
    //repository는 더 기계적으로 용어 선택
    //전체 회원 조회
    public List<Member> findMembers(){
            return memberRepository.findAll();
    }
    public  Optional<Member> findOne(Long memberId){
        return  memberRepository.findById(memberId);
    }

}
