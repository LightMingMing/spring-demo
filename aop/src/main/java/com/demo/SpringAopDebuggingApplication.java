package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.StringJoiner;

/**
 * Spring AOP Debugging
 *
 * @author MingMing Zhao
 */
@Slf4j
public class SpringAopDebuggingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(DebuggingConfiguration.class);

        GreetingService greetingService = ctx.getBean(GreetingService.class);
        printClazz(greetingService.getClass());
        System.out.println(greetingService.greet("Hello, Jdk Dynamic Proxy"));

        EchoService echoService = ctx.getBean(EchoService.class);
        printClazz(echoService.getClass());
        System.out.println(echoService.echo("Hello, Cglib Proxy"));
    }

    private static void printClazz(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("class [").append(clazz.getName()).append("]\n");

        if (clazz.getSuperclass() != null) {
            sb.append("super class [").append(clazz.getSuperclass().getName()).append("]\n");
        }

        StringJoiner joiner = new StringJoiner(", ");
        for (Class<?> ifc : clazz.getInterfaces()) {
            joiner.add(ifc.getName());
        }
        sb.append("interfaces [").append(joiner).append("]");
        System.out.println(sb);
    }

    // 启用自动代理
    @Configuration
    @EnableAspectJAutoProxy
    static class DebuggingConfiguration {

        // ProxyFactoryBean 生成 JDK 动态代理类
        @Bean
        public ProxyFactoryBean greetingService() {
            ProxyFactoryBean factoryBean = new ProxyFactoryBean();
            factoryBean.setTarget(new GreetingServiceImpl());
            factoryBean.setInterceptorNames("tracingInterceptor");
            return factoryBean;
        }

        // ProxyFactoryBean 生成 CGLIB 动态代理类
        @Bean
        public ProxyFactoryBean echoService() {
            ProxyFactoryBean factoryBean = new ProxyFactoryBean();
            factoryBean.setTarget(new EchoService());
            factoryBean.setInterceptorNames("tracingInterceptor");
            return factoryBean;
        }

        // 通知 Advise
        @Bean
        public TracingInterceptor tracingInterceptor() {
            return new TracingInterceptor();
        }

        // 切面: 自动代理
        @Bean
        public TracingAspect tracingAspect() {
            return new TracingAspect();
        }

    }
}
