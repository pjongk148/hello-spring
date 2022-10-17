package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //jpa는 entitiymanager로 동작, 내부적으로 db와 통신등의 처리를 함
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);

    }

    @Override
    public Optional<Member> findByName(String name) {
        //findbyname같은 경우 객체지향쿼리사용
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {  //jpql이라는 객체 대상 쿼리언어(sql로 번역됨)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
