package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.util.Properties;

/**
 * @ClassName kbu_KafkaProducer
 * Description TODO
 * @Author wangjm
 * Date 2019/6/10 14:10
 * @Version 1.0
 */
public class kbu_KafkaProducer implements Runnable {
    private static String path;
    private static String topic;
    private static int interval;

    public kbu_KafkaProducer(String path, String topic, int interval) {
        this.path = path;
        this.topic = topic;
        this.interval = interval;
    }

    private void write(String s) throws IOException {
        File file = new File(s);
        if (!file.exists()) {
            System.out.println("file:" + path + " does not exit");
            return;
        }
        InputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        Producer<String, String> producer = genarateProducer();
        String line;
        int ind = 0;
        while ((line = br.readLine()) != null) {
            producer.send(new ProducerRecord<String, String>
                    (topic, line));
            System.out.println(ind + "  :  " + line);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ind++;
        }
        br.close();
        fis.close();
    }

    private Producer<String, String> genarateProducer() {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "10.11.142.242:6667");
        props.put("bootstrap.servers", "hadoop01:9092");
        props.put("group.id", ProducerLauncher.groupid);
        props.put("acks", "all");
        props.put("delivery.timeout.ms", 30000);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        return producer;
    }

    @Override
    public void run() {
        try {
            write(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
