package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Autowired MemberRepository memberRepository;
//    @Autowired DiscountPolicy discountPolicy;
//
//    @Bean
//    OrderService orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        return new OrderServiceImpl(memberRepository, discountPolicy);
//    }

    @Bean("memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
