package hello.hellospring;

import hello.hellospring.AOP.TimeTraceAop;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    //spring data jpa가 interface만 잇으면, 구현체를 자동으로 만들어 bean에 등록함
    //그냥 가져다 쓰면 된다.
    private final MemberRepository memberRepository;

    //injection 받는다.
    //이런 경우에는 spring config에서 만들어 놓은 것을 찾는다.
    //인터페이스만 있음
    //spring dat jpa가 인터페이스에 데한 구현체를 만들고 spring에 등록함
    @Autowired
    public SpringConfig(MemberRepository memberRepositor) {
        this.memberRepository = memberRepositor;
    }

    //다른 코드의 변경없이 h2 database 사용 가능
    //구현체만 바꾸면 된다.

    //OCP
    //기능 추가에는 열려 있고, 수정, 변경에는 닫혀있다.

    // 다형성을 잘 이용하면 기능을 완전히 변경해도 실제 앱 동작 코드 변경 필요가 없다.
//    private DataSource dataSosure;
//    @Autowired
//    public SpringConfig(DataSource dataSosure){
//        this.dataSosure = dataSosure;
//    } //jdbc 사용시

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//
//        return new JpaMemberRepository(em);
//        return new JdbcTemplateMemberRepository(dataSosure);
//        return new JdbcMemberRepository(dataSosure);
//        return new MemoryMemberRepository();
//    }
    //spring이 configuration을 읽고 bean에 등록
    //MemberService, MemberRepository둘다 등록해서 MemberService에 넣어준다.


    //Conponent 스캔보다 따로 설정 파일을 만드는 경우
    // 나중에 Repository를 DB로 바꾸더라도 return만 변경하면 된다.
    // 다른 코드의 변경 필요 없음

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    } //config 등록을 더 선호한다.
    //순한 참조 에러가 나므로 AOP 대상에서 SpringConfig를 빼야함
}
