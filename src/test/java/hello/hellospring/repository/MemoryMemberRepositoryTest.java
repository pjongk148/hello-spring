package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; //static import로 편리하게 사용

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트는 작성 순서에 상관없이 실행되는데, 이때 순서에 상관없이 각각의 테스트를 설계해야함
    // 따라서 repository 에 테스트 후 모두 비워주는 코드를 작성해줘야 함 이러한 테스트 주도개발을 TDD라고 함
    @AfterEach //AfterEach는 메서드 실행이 끝날때마다 동작을 실행하는 것
    public void afterEach() {
        repository.clearStore();
    }



    @Test //junit의 test 애너테이션을 통해 테스트 가능
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //Optional에서 값을 가져오는 방식은 get()
        assertThat(member).isEqualTo(result); //org.assertj.assertthat을 통해 test시 결과값 없이 테스트 가능
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
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
