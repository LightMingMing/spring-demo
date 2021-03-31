package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 消息发送
 *
 * @author MingMing Zhao
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {

    private final KafkaTemplate<String, Message> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ProducerController(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // 异步
    @PostMapping("/async")
    public String asyncSend(@RequestBody Message message) {
        kafkaTemplate.send("topic1", message.getCertId(), message)
                .addCallback(result -> {
                }, this::exceptionHandler);
        return "ok";
    }

    // 同步
    @PostMapping("/sync")
    public String syncSend(@RequestBody Message message) {
        try {
            kafkaTemplate.send("topic1", message.getCertId(), message).get(10, TimeUnit.SECONDS);
        } catch (ExecutionException | TimeoutException | InterruptedException e) {
            exceptionHandler(e);
            return "error";
        }
        return "ok";
    }

    private void exceptionHandler(Throwable e) {
        logger.error("消息发送失败", e);
    }

}
