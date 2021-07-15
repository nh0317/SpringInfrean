package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository 에서 공통 기능을 제공함
// name, email등으로 찾는 건 비지니스가 다르므로 공통으로 기능을 제공할 수 없음
// 그래서 findByName 추가

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    //JPQL select m from Member m where m.name =?
    //위와 같은 형태로 짜준다.
    // And Or 등도 가능
    // 단순한 것은 인터페이스만으로 구현 가능
    // 페이징 기능까지 자동 제공
    @Override
    Optional<Member> findByName(String name);
}

// 실무에서는 JPA의 스프링 데이터 JPA를 기본으로 사용, 복잡한 동적 쿼리는 Querydsl이라는 라이브러리 사용
// Querydsl 사용시 쿠리도 자바 코드로 안전하게 작성 가능
//이렇게 해결이 안되면 jpa가 제공하는 native 쿼리 사용가능
//필요시 스프링 JdbcTemplate 사용가능

//
