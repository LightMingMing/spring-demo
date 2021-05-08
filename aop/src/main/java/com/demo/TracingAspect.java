package com.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 基于注解的自动代理
 *
 * @author MingMing Zhao
 */
@Aspect
public class TracingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // CGLIB
    // class [com.demo.EchoService$$EnhancerBySpringCGLIB$$33be1e57]
    // super class [com.demo.EchoService]
    // interfaces [org.springframework.aop.SpringProxy, org.springframework.aop.framework.Advised, org.springframework.cglib.proxy.Factory]

    // JDK
    // class [com.sun.proxy.$Proxy20]
    // super class [java.lang.reflect.Proxy]
    // interfaces [com.demo.GreetingService, org.springframework.aop.SpringProxy, org.springframework.aop.framework.Advised, org.springframework.core.DecoratingProxy, java.io.Serializable]

    // 这里不要使用 EchoService ( 对 EchoService 的 CGLIB 代理类进行 JDK 动态代理 丢失原始类型类型), 会发生如下问题
    // Bean named 'echoService' is expected to be of type 'com.demo.EchoService' but was actually of type 'com.sun.proxy.$Proxy20'
    @Before("execution(* com.demo.GreetingService.greet(..))")
    public void before(JoinPoint jp) {
        log.info("Before " + getJoinPointDescription(jp));
    }

    @AfterReturning(value = "execution(* com.demo.GreetingService.greet(..))", returning = "retValue")
    public void afterReturning(JoinPoint jp, Object retValue) {
        log.info("AfterReturning " + getJoinPointDescription(jp) + ", return " + retValue);
    }

    @Around("execution(* com.demo.GreetingService.greet(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Entering " + getJoinPointDescription(pjp));
        try {
            return pjp.proceed();
        } finally {
            log.info("Existing " + getJoinPointDescription(pjp));
        }
    }

    protected String getJoinPointDescription(JoinPoint jp) {
        Object target = jp.getThis();
        Assert.state(target != null, "Target must not be null");
        String className = target.getClass().getName();
        return "method '" + jp.getSignature().getName() + "' of class [" + className + "]";
    }
}
