package spark

import org.apache.spark.{SparkConf, SparkContext}

object Demo1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("test").setMaster("local")
    val sc = new SparkContext(conf)

   // sc.textFile("file:///E:/target/abc.csv").foreach(println)

//    sc.sequenceFile("file:///E:/target/abc.csv").map(x=>{
//      x._2
//    }).foreach(println)
    //访问不了集群
//sc.wholeTextFiles("ftp://ipms:ipms!23$@192.168.1.202:22/home/ipms/a.txt").map(x=>x._2)
//    .foreach(println)
    sc.parallelize(Array(1,2,3,4,5))
      .flatMap(x=>x.toString)



    sc.stop()

  }

}
