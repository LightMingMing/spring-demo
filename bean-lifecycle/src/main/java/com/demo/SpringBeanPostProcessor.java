package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SpringBeanPostProcessor() {
        logger.info("new SpringBeanPostProcessor()");
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass.isAssignableFrom(SpringBean.class))
            logger.info("postProcessBeforeInstantiation({}, {})", beanClass, beanName);
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringBean) {
            logger.info("postProcessAfterInstantiation({}, {})", bean, beanName);
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringBean) {
            logger.info("postProcessProperties({}, {}, {})", pvs, bean, beanName);
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringBean) {
            logger.info("postProcessBeforeInitialization({}, {})", bean, beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringBean) {
            logger.info("postProcessAfterInitialization({}, {})", bean, beanName);
        }
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringBean) {
            logger.info("postProcessBeforeDestruction({}, {})", bean, beanName);
        }
    }
}
