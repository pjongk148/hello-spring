package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforeEach() { //테스트 실행할 때마다 독립적으로 객체 생성
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //memberservice와 repository공유
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() { //테스트는 메서드명 한글로 작성해도 상관 x
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); //방금 저장한 멤버의 이름 일치 여부 확인
    }
        // -> 테스트 작성시 given : 이렇게 주어질 때 when: 언제 then: 이렇게 되어야해 이 세 구조를 가지면 좋다(pseudo code 느낌)

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //이 테스트를 위해 try catch를 넣는 것은 좋은 방식 x, assertThrows를 통해 저 예외가 터지는지 확인
        //예외를 e로 받아서 예외메시지가 일치하는지 확인하기

        //then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}