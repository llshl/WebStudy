package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.memeber.MemberRepository;
import hello.core.memeber.MemberService;
import hello.core.memeber.MemberServiceImpl;
import hello.core.memeber.MemorymemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//이 클래스가 MemberServiceImpl과 OrderServiceImpl을 생성해주면서 동시에 그 구현체들의 의존을 넣어준다(pdf 14페이지)
//중요한것은 구현체들은 인터페이스에만 의존할수있다는것, 관심사의 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리됐다.

//설정정보 어노테이션
@Configuration
public class AppConfig {

    //생성자를 통해서 의존넣어줌 생성자주입
    //메인메서드(MemberApp)에서 얘를 호출하면 MemberServiceImpl을 생성함과 동시에 생성자 주입해서 줌
    //구현체 생성(의존도 넣어줌)
    
    //스프링컨테이너에 등록을 해주는 어노테이션
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    //구현체 생성(의존도 넣어줌)
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    //의존주입
    @Bean
    public MemberRepository memberRepository() {
        return new MemorymemberRepository();
    }
    //의존주입
    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
        //이런식으로 의존할 구현체들을 바꾸는것은 여기서만 하면 되게된다
    }
    
    //코드만 봐도 그게 뭐하는 역할인지 보여야한다
    //이 AppConfig클래스로 인해서 애플리케이션을 사용영역과 구현영역으로 나눔
}
