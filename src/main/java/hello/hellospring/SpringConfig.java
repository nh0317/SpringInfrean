package hello.hellospring;

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

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){

        return new JpaMemberRepository(em);
//        return new JdbcTemplateMemberRepository(dataSosure);
//        return new JdbcMemberRepository(dataSosure);
//        return new MemoryMemberRepository();
    }
    //spring이 configuration을 읽고 bean에 등록
    //MemberService, MemberRepository둘다 등록해서 MemberService에 넣어준다.


    //Conponent 스캔보다 따로 설정 파일을 만드는 경우
    // 나중에 Repository를 DB로 바꾸더라도 return만 변경하면 된다.
    // 다른 코드의 변경 필요 없음
}
