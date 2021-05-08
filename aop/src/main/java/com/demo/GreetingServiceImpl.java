package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GreetingService 接口实现
 *
 * @author MingMing Zhao
 */
public class GreetingServiceImpl implements GreetingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String greet(String message) {
        logger.info("Executing greet({})", message);
        return message;
    }
}
