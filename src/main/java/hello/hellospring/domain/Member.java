package hello.hellospring.domain;

// 인터페이스인 jpa의 구현체인 hibernate 사용
// jpa는 annotation 을 통해 db와 객체를 매핑해줌
import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //db가 id를 자동생성(pk)
    private Long id;

    private String name;

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
