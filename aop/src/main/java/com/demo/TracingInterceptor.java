package com.demo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 简单 Trace
 *
 * @author MingMing Zhao
 */
public class TracingInterceptor implements MethodInterceptor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String invocationDescription = getInvocationDescription(invocation);
        log.info("Entering " + invocationDescription);
        try {
            Object result = invocation.proceed();
            log.info("Exiting " + invocationDescription);
            return result;
        } catch (Throwable ex) {
            log.error("Exception thrown in " + invocationDescription, ex);
            throw ex;
        }
    }

    protected String getInvocationDescription(MethodInvocation invocation) {
        Object target = invocation.getThis();
        Assert.state(target != null, "Target must not be null");
        String className = target.getClass().getName();
        return "method '" + invocation.getMethod().getName() + "' of class [" + className + "]";
    }
}
