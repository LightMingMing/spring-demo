package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 消息消费
 *
 * @author MingMing Zhao
 */
@Service
public class MessageConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 消费者1
    @KafkaListener(id = "c1", groupId = "group", topics = "topic1")
    public void messageHandler1(@Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                                @Header(KafkaHeaders.OFFSET) List<Long> offsets,
                                @Payload Message message) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
        logger.info("partition: {}, offset:{}, certId: {}", partitions, offsets, message.getCertId());
    }

    // 消费者2
    @KafkaListener(id = "c2", groupId = "group", topics = "topic1")
    public void messageHandler2(@Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                                @Header(KafkaHeaders.OFFSET) List<Long> offsets,
                                @Payload Message message) {
        logger.info("partition: {}, offset:{}, certId: {}", partitions, offsets, message.getCertId());
    }

}
