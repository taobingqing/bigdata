package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

/**
 * @ClassName kbu_KafkaConsumer
 * Description TODO
 * @Author wangjm
 * Date 2019/6/13 10:02
 * @Version 1.0
 */
public class kbu_KafkaConsumer {

    private static String topic = "r_volte_kb_u";
    private static String path = "E:\\eastcom\\0611infost&sipcall\\0619\\kbu0619_run2.txt";

    public static void main(String[] args) throws Exception {
        File file = new File(path);
        OutputStream oos=new FileOutputStream(file);
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(oos));

        if (!file.exists()) {
            System.out.println("file in : " + path + " does not exit");
            return;
        }
        KafkaConsumer<String, String> consumer = getKafkaConsumer();
        int ind=0;
        try {
//        while (!consumer.poll(10000).isEmpty()) {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    bw.write(record.value());
                    bw.newLine();
                    System.out.println(ind+" : value = " + record.value());
                    ind++;
                    bw.flush();
                }
            }
        } finally {
            bw.close();
            oos.close();
            consumer.close();
            System.out.println("ENDED!");
        }
    }

    private static KafkaConsumer<String, String> getKafkaConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.204:9092");
        props.put("group.id", ProducerLauncher.groupid);
        props.put("enable.auto.commit", "true");
//        props.put("auto.offset.reset", "earliest");
        props.put("auto.offset.reset", "latest");
        props.put("auto.commit.interval.ms", "1000");
//        props.put("fetch.max.bytes", "1024");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer =
                new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(topic));
        return consumer;
    }
}
