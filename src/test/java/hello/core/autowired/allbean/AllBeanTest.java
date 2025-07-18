package hello.core.autowired.allbean;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);
        int fixDiscountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(fixDiscountPrice).isEqualTo(1000);


        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    @RequiredArgsConstructor
    static class DiscountService {
//        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        private final Provider<Map<String, DiscountPolicy>> policyMapProvider;

//        @Autowired
//        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
//            this.policyMap = policyMap;
//            this.policies = policies;
//
//            System.out.println("policyMap = " + policyMap);
//            System.out.println("policies = " + policies);
//        }

        public int discount(Member member, int price, String discountCode) {
            Map<String, DiscountPolicy> policyMap = policyMapProvider.get();

            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            return discountPolicy.discount(member, price);
        }
    }
}
