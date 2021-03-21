package com.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class SpringBean implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SpringBean.class);

    @Value("${message:hello}")
    private String message;

    private FooBean fooBean;

    // 1. 构造方法
    public SpringBean() {
        logger.info("(1) new SpringBean()");
    }

    // 2. 属性注入
    @Autowired
    public void setFooBean(FooBean fooBean) {
        logger.info("(2) setFooBean({})", fooBean);
        this.fooBean = fooBean;
    }

    // 3. Aware接口
    @Override
    public void setBeanName(String name) {
        logger.info("(3) setBeanName({})", name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        logger.info("(3) setClassLoader({})", classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.info("(3) setBeanFactory({})", beanFactory);
    }

    // 4. postProcessBeforeInitialization
    @Override
    public void setEnvironment(Environment environment) {
        logger.info("(4) setEnvironment({})", environment);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("(4) setApplication({})", applicationContext);
    }

    @PostConstruct
    public void postConstruct() {
        logger.info("(4) @PostConstruct");
    }

    // 5. 初始化
    @Override
    public void afterPropertiesSet() {
        logger.info("(5) afterPropertiesSet()");
    }

    // 6. postProcessBeforeDestruction
    @PreDestroy
    public void preDestroy() {
        logger.info("(6) @PreDestroy");
    }

    // 7. 销毁
    @Override
    public void destroy() {
        logger.info("(7) destroy()");
    }

    @Component
    static class FooBean {

    }
}
