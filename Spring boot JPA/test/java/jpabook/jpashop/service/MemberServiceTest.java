package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)    //Junit실행시 스프링과 함께 실행하자는뜻
@SpringBootTest //스프링부트를 띄운상태로 테스트할려면 이걸쓴다. 이거 없으면 @Autowired 안먹는다
@Transactional  //롤백위해서 사용
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception{
        //given = 이게 주어졌을때
        Member member = new Member();
        member.setName("kim");

        //when = 이렇게 하면
        Long saveId = memberService.join(member);

        //then = 이렇게 된다를 검증
        //em.flush(); //영속성 컨텍스트의 변경 내용을 DB에 반영하는것(DB로 쿼리를 보내준다), 즉 영속성 컨텍스트의 변경사항들과 DB의 상태를 맞추는 작업
        assertEquals(member, memberRepository.findOne(saveId)); //넣기 전 멤버랑 DB에서 빼온 멤버랑 PK가 같으면 같은셈쳐진다, True False반환
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예제() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim1");
        Member member2 = new Member();
        member2.setName("kim1");

        //when
        /*
        memberService.join(member1);
        try {
            memberService.join(member2);    //같은 이름이므로 예외발생해야한다, try catch가 아니므로 throw를 통해 여기까지 튀어나옴
        }catch (IllegalStateException e){
            return; //예외발생시 정상리턴
        }
        */
        
        //try catch로 IllegalStateException 발생시 정상리턴해도 되지만 @Test뒤에 (expected = IllegalStateException.class)를 통해서
        //IllegalStateException이 발생되기를 기대한다는 뜻. 즉 이게 IllegalStateException이 어디서든간 발생하면 정상적인 테스트라는 뜻
        memberService.join(member1);
        memberService.join(member2);
        //then
        fail("예외가 발생해야 한다.");   //fail이란 이 코드가 실행됐다면 그것은 잘못된것이다라는뜻 여기까지 실행되면 에러난다.
        //위에서 return안됐으면 여기 실행되며 테스트 실패
    }
}