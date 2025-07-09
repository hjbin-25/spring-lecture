package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hello.core.AppConfig;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Component
public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void fieldInjectionTest() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        assertThrows(NullPointerException.class, // 잘못된 참조로 인한 널 포인터 참조 에러 발생확인
                () -> orderService.createOrder(1L, "itemA", 10000));
//        orderService.createOrder(1L, "itemA", 10000);
//        orderService.setMemberRepository(new MemoryMemberRepository());
        orderService.setDiscountPolicy(new RateDiscountPolicy());
    }
}