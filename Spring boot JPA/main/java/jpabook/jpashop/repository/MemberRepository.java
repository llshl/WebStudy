package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //이걸 붙혀야 컴포넌트스캔을 통해 스프링의 관리하에 들어감
@RequiredArgsConstructor
public class MemberRepository {
    /*
    @PersistenceContext
    private EntityManager em;   //스프링이 엔티티매니저를 만들어서 여기에 주입
    */ //이렇게 해도되는데

    //롬복을 통해 주입(생성자주입인데 롬복이 생성자 자동생성해줌)
    //원래라면 @PersistenceContext를 통해 해야하지만 스프링부트(스프링 데이터jpa)는 @Autowired로도 주입이 된다. @Autowired로도 된다는것은 생성자로해도 된다는것
    private final EntityManager em;



    public void save(Member member){

        em.persist(member);
    }

    public Member findOne(Long id){

        return em.find(Member.class, id);   //find(타입, PK)
    }

    public List<Member> findAll(){
        //sql은 테이블을 대상으로하는 쿼리, jpql은 엔티티를 대상으로 하는 쿼리
        //그래서 쿼리를 보면 *대신에 Member의 m을 쓴다
        return em.createQuery("select m from Member m",Member.class).getResultList();//따로 JPQL를 작성해야함  createQuery(spql, 반환타입)
    }

    public List<Member> findByName(String name){
        //jpql문법은 살짝 다름, m.name이 :name과 같은것을 고르는것, :name은 setParameter로 지정
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name).getResultList();
    }
}
