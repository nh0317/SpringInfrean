package hello.hellospring.domain;
import javax.persistence.*;

// jpa가 관리하는 entity
// java 객체와 DB를 매핑한다.
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // ID를 DB에서 생성하는 전략
    private Long id;

    private String name;
    //getter setter 단축기
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
