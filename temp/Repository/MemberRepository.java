package com.example.BootStrap_AdminTest.Repository;

import com.example.BootStrap_AdminTest.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(String id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        //sql은 테이블을 대상으로하는 쿼리, jpql은 엔티티를 대상으로 하는 쿼리
        //그래서 쿼리를 보면 *대신에 Member의 m을 쓴다
        return em.createQuery("select m from Member m",Member.class).getResultList();//따로 JPQL를 작성해야함  createQuery(spql, 반환타입)
    }
}
