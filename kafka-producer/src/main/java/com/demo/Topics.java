package com.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * 主题配置
 *
 * @author MingMing Zhao
 */
@Configuration(proxyBeanMethods = false)
public class Topics {
    @Bean
    public NewTopic topic1() {
        // 分区数3、分区副本数1、旧日志片段清理策略
        return TopicBuilder.name("topic1")
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }

    public NewTopic topic2() {
        // 消息压缩
        return TopicBuilder.name("topic2")
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                .build();
    }
}
