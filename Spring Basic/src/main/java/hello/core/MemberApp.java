package hello.core;

import hello.core.memeber.Grade;
import hello.core.memeber.Member;
import hello.core.memeber.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig_Java appConfig = new AppConfig_Java();
//        MemberService memberService = appConfig.memberService();    //memberService에는 생성자주입을 통한 memberServiceImpl이 들어가있다

        //스프링컨테이너, 즉 얘가 Bean들(객체들)을 다 관리한다
        //AppConfig클래스의 Bean들을 스프링컨테이너에 다 넣어서 관리시작
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //memberService라는 이름의 빈을 가져온다. 반환타입은 MemberService
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        //MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = "+member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
