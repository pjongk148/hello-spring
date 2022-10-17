package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional은 만약 null값을 가져올 경우 감싸서 반환해주는 방식
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
