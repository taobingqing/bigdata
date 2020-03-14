package com.eastcom.driver

import java.io.IOException

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, FileUtil, Path}
import org.apache.hadoop.io.compress.GzipCodec
import org.apache.log4j.Logger
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext

object SqlTest {

  private final val logging = Logger.getLogger(getClass)

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
    sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    sparkConf.set("spark.kryo.referenceTracking", "false")
    sparkConf.set("spark.kryo.registrationRequired", "false")

    val sc = new SparkContext(sparkConf)
    val sqlContext = new HiveContext(sc)

    val sql = " select rpt_time ,imei_tac,mme_ip,eci,attach_attempt,attach_accept,attach_latency,act_def_bearer_attempt,act_def_bearer_accept,act_def_bearer_latency,actdedicatedepsbearer_attempt,actdedicatedepsbearer_accept,actdedicatedepsbearer_latency,actdedeps_qci_1_attempt,actdedeps_qci_1_succ,actdedeps_qci_2_attempt,actdedeps_qci_2_succ,actdedeps_mod_qci_1_attempt,actdedeps_mod_qci_1_succ,actdedeps_mod_qci_2_attempt,actdedeps_mod_qci_2_succ,pdn_conn_attempt,pdn_conn_accept,path_switch_attempt,path_switch_accept,path_switch_latency,ho_required,ho_command,ho_latency,actdedeps_ue_attempt,actdedeps_ue_accept,initial_ctxt_setup_attempt,initial_ctxt_setup_succ,radio_release,radio_release_abnormal,qci1_drop_num,qci1_num,qci5_drop_num,qci5_num from ipms.dw_ft_se_vt_s1mme_cl2_tmt_d where day_partition=cast(20191203 as bigint)"
    val startTime = System.currentTimeMillis()
    val df = sqlContext.sql(sql)
    val rddSql = df.rdd

    val sqlStopTime = System.currentTimeMillis()
    logging.info(s"执行sql耗时:  ${sqlStopTime - startTime} 毫秒")
    //val hdfsPath = "/user/ipms/test/gz_test06"
    // # .coalesce(60)
    val outPath = "/user/ipms/test/tmp/gz_test01"
    val headOutPath = "/user/ipms/test/tmp/head01"
    val head = "a|b|c|d|e|f"
    val rddHead: RDD[String] = sc.makeRDD(List(head))
    //    val index = outPath.lastIndexOf("/")
    rddHead.coalesce(1).saveAsTextFile(headOutPath,classOf[GzipCodec])
   // rddSql.map(r => r.mkString("@")).coalesce(30).saveAsTextFile(outPath,classOf[GzipCodec])
    val DELIMITER ="|"
    rddSql.map(r => r.mkString(DELIMITER))
      .map(s => {
        var arr = s.split(DELIMITER)
        for(i <- 0 until  arr.length){
          if (arr(i) ==null || arr(i).equals("null")){
            arr(i)=""
          }
        }
        arr.mkString(DELIMITER)
      }).coalesce(30).saveAsTextFile(outPath)

    val finalOutPath = "/user/ipms/test/tmp/final.gz.tmp"

//    val conf = new Configuration
//    val fs: FileSystem = FileSystem.get(conf)
//    val src: Path = new Path(outPath)
//    val src1:Path = new Path(headOutPath)
//    val dst: Path = new Path(finalOutPath)

//    FileUtil.copyMerge(src1.getFileSystem(conf), src1, dst.getFileSystem(conf), dst, false, conf, null)
//
//    MergeUtils.copyMerge(src.getFileSystem(conf), src, dst.getFileSystem(conf), dst, false, conf, null)




    //    df.write.mode("overwrite").format("com.databricks.spark.csv")
    //      .option("header",false)
    //      .option("codec","gzip")
    //      .option("delimiter","|")
    //      .option("dateFormat","yyyy-MM-dd HH:mm:ss")
    //      .save(outPath)

    //    val random = new java.util.Random()
    //    rddSql.map(x => (random.nextInt(10240),x)).partitionBy(new HashPartitioner(60))
    //        .map(x =>x._2).saveAsTextFile(hdfsPath,classOf[GzipCodec])


    logging.info(s"写到hdfs耗时:  ${System.currentTimeMillis() - sqlStopTime} 毫秒")


  }



}
