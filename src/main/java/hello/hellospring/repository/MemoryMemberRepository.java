package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository
public class MemoryMemberRepository implements  MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;//실무에서 동시성 문제 때문에 atom long 사용

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //결과가 없는 경우? optional 사용 -> 클라이언트에서 처리 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();//하나 찾으면  그대로 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    //어떻게 동작하는지 검증하는 방법이 testcase 작성

    public  void clearStore(){
        store.clear();
    }
}
