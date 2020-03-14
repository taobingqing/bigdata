package sparkstreaming_to_kafka

import java.util.Properties

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}

object SscMain {
  def main(args: Array[String]): Unit = {
    val sc = new SparkConf().setAppName("test01").setMaster("local")
    val context = new SparkContext(sc)
    val ints = Seq(1, 2, 3, 4)
    context.parallelize(ints).foreach(i => println(i))

    context.stop()
    //
    //    val ssc = new StreamingContext(sc,Duration(5))
    //
    //    val topics =""
    //    val broker_list = ""
    //    KafkaUtils.createDirectStream(ssc,LocationStrategies.PreferConsistent,ConsumerStrategies.Subscribe(Array(topics),kafkaParams = Map(
    //      "bootstrap.servers" -> broker_list,//用于初始化链接到集群的地址
    //      "key.deserializer" -> classOf[StringDeserializer],
    //      "value.deserializer" -> classOf[StringDeserializer],
    //      //用于标识这个消费者属于哪个消费团体
    //      "group.id" -> "gmall_consumer_group",
    //      //如果没有初始化偏移量或者当前的偏移量不存在任何服务器上，可以使用这个配置属性
    //      //可以使用这个配置，latest自动重置偏移量为最新的偏移量
    //      "auto.offset.reset" -> "latest",
    //      //如果是true，则这个消费者的偏移量会在后台自动提交,但是kafka宕机容易丢失数据
    //      //如果是false，会需要手动维护kafka偏移量
    //      "enable.auto.commit" -> (true: java.lang.Boolean)) ))
    //
    //  }

  }
}
