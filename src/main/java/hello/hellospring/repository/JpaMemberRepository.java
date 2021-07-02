package hello.hellospring.repository;


import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements  MemberRepository {

    //entity manager로 동작함
    //spring에서 만들어주므로 injection 받아 사용하면 된다.
    //내부적으로 dataSource 등의 정보를 가지고 있다.
    private  final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 자동으로 DB에 insert 해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // 여러개의 리스트를 가진 경우 Query를 작성해야하지만
    // 단건을 조회하는 경우 Query를 작성할 필요 없다.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //객체 자체를 조회한다.
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
