package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.memeber.Member;
import hello.core.memeber.MemberRepository;
import hello.core.memeber.MemorymemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

/*
    private final MemberRepository memberRepository = new MemorymemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();    //이렇게 코드를 직접적으로 수정하면단됨 => OCP위반
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //OrderServiceImpl이 DiscountPolicy뿐만아니라 DiscountPolicy의 구현체들에게도 의존한다
    //DIP위반 => 우린 인터페이스에만 의존해야한다
*/
    //인터페이스만 의존하도록 하는방법
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //구현체를 뺐다 => DIP는 지켰지만 null익셉션이 뜬다
    //그렇다면 어떻게 우리는 DIP를 지키며 널포인트익셉션도 피할수있을까? => 누군가 대신 구현체를 주입해줘야한다

    //생성자로 구현체 주입 -> 생성자주입, memberRepository에 MemorymemberRepository가 들어올지 DB가 들어올지 여기선 모른다
    //또 discountPolicy에도 FixdiscountPolicy가 들어올지 RatediscountPolicy가 들어올지 여기선 모른다. 이게 옳바른 DIP설계

/*
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {    //이렇게 세터DI로 해도된다. 위에 final은 빼줘야함
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println(" discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
*/

    //Lonbok으로 생성자 생성해줄수도 있다
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy  DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //결국 생성자주입을 통해 이 클래스(OrderServiceImpl은 DIP를 지키며 인터페이스들에만 의존 할 수있다)

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //discount에 대한 정보는 몰라도 그냥 여기서 사용만 하면되기에 좋은 설계

        return new Order(memberId, itemName, itemPrice, discountPrice); //주문 생성하여 반환
    }

    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}