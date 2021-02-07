package hello.core.singletonTest;

import hello.core.AppConfig;
import hello.core.memeber.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        System.out.println(memberService1);
        System.out.println(memberService2); //두개가 다른 객체다. 호출할때마다 새로운 객체로 반환된다
        Assertions.assertThat(memberService1).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void SingletonserviceTest(){
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();
        System.out.println(instance1);  //이 둘은 같은 객체이다
        System.out.println(instance2);
        Assertions.assertThat(instance1).isSameAs(instance2);
        //isSameAs은 ==비교
        //isEqualsTo는 .equals비교
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        System.out.println(memberService1);
        System.out.println(memberService2); //스프링 컨테이너로 호출한 객체는 같은 객체다
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}

