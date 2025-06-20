package hello.core.discount;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.Grade;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}