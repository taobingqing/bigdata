package sparkstreaming_to_kafka

import java.util.concurrent.Future

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}

class broadcastKafkaProducer[K, V](createproducer: () => KafkaProducer[K, V]) extends Serializable {
  lazy val producer = createproducer()

  def send(topic: String, key: K, value: V): Future[RecordMetadata] = producer.send(new ProducerRecord[K, V](topic, key, value))

  def send(topic: String, value: V): Future[RecordMetadata] = producer.send(new ProducerRecord[K, V](topic, value))

}

object broadcastKafkaProducer {


  import scala.collection.JavaConversions._

  def apply[K, V](config: Map[String, Object]): broadcastKafkaProducer[K, V] = {
    val createProducerFunc = () => {
      val producer = new KafkaProducer[K, V](config)
      sys.addShutdownHook({producer.close()})
      producer
    }
    new broadcastKafkaProducer(createProducerFunc)
  }

  def apply[K, V](config: java.util.Properties): broadcastKafkaProducer[K, V] = apply(config.toMap)
}