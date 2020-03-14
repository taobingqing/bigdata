package sparkstreaming_to_kafka_optimizer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
import java.util.concurrent.Future;
import java.io.Serializable;

public class broadcastKafkaProducer implements Serializable {

    private static KafkaProducer produecer;
    private Properties properties;

    private broadcastKafkaProducer(Properties properties){
        this.properties=properties;
    }

    public KafkaProducer getProduecer(){
        if(produecer == null){
            return new KafkaProducer<>(properties);
        }else{
            return produecer;
        }
    }

    public void send(String topic, String value) {
        produecer.send(new ProducerRecord(topic,value));
    }

    public Future send(String topic, String key, String value) {
        Future future = produecer.send(new ProducerRecord(topic, key, value));
        return future;
    }

}
