package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.memeber.MemberService;
import hello.core.memeber.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈을 이름으로 조회하기")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //memberService가 MemberServiceImpl의 인스턴스라면 테스트 통과
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈을 이름없이 타입으로 조회하기")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        //memberService가 MemberServiceImpl의 인스턴스라면 테스트 통과
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회하기")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        //memberService가 MemberServiceImpl의 인스턴스라면 테스트 통과
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회하기 실패")
    void findBeanByNameX(){
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxx", MemberService.class));
        //Junit의 Assertion사용
        //xxx라는 이름의 빈을 조회했을때 NoSuchBeanDefinitionException라는 예외가 떠야 테스트통과 라는뜻

    }
}
