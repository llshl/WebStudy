package hello.core.discount;

import hello.core.memeber.Grade;
import hello.core.memeber.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;   //vip는 고정할인 1000원

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {   //Enum타입은 == 으로하는게 맞다
            return discountFixAmount;
        }
        return 0;
    }
}
