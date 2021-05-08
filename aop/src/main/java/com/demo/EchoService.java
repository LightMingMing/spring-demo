package com.demo;

/**
 * EchoService 不实现某个接口, 测试 CGLIB 动态代理
 *
 * @author MingMing Zhao
 */
public class EchoService {

    public String echo(String message) {
        return message;
    }
}
