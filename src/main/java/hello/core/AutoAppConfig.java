package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    @Bean("memoryMemberRepository")
    @Primary
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
