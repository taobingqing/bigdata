package com.taobq.test.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @link {https://blog.csdn.net/songzehao/article/details/106234587}
 */

@Slf4j
public class KafkaConsumerTest {



    public static void KafkaConsumerTest() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "100.1.4.16:9092,100.1.4.17:9092,100.1.4.18:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 默认每次轮询最多取多少条消息，默认500
        properties.put("max.poll.records", 1);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        String topic = "test_topic";
        TopicPartition tp = new TopicPartition(topic, 0);
        // 指定分区
        consumer.assign(Collections.singletonList(tp));
        log.info("本topic下所有的分区：{}", consumer.partitionsFor(topic));
        // 获取消费者被分配到的分区（注意，assign模式会直接返回手动指定的分区，而subscribe模式等到自动分配分区后才有返回）
        log.info("本消费者分配到的分区：{}", consumer.assignment());
        // 为某个指定分区任意位置、起始位置、末尾位置为起始消费位置(offset默认从0开始)
        // 注意若分配的offset<分区最小的offset(可能kafka删除策略影响，比如默认删除超过7d的数据导致最小offset值变化)，将从最新offset处监听消费
//         consumer.seek(tp, 5);
//         consumer.seekToBeginning(Arrays.asList(tp));
//         consumer.seekToEnd(Collections.singletonList(tp));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000L).toMillis());
            log.info("本次轮询到：{}条", records.count());
            for (ConsumerRecord<String, String> record : records) {
                log.info("-------消息来了：topic={}, partition={}, offset={}, value={}", record.topic(), record.partition(),
                        record.offset(), record.value());
            }
        }
    }
}
