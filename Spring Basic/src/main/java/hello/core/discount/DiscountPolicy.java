package hello.core.discount;

import hello.core.memeber.Member;

public interface DiscountPolicy {

    //리턴값은 할인금액
    int discount(Member member, int price);
}
