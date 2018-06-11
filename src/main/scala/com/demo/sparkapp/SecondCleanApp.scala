package com.demo.sparkapp

import org.apache.spark.sql.{SaveMode, SparkSession}


/**
  * 信息转化
  * 【输入】：访问时间、访问URL、耗费的流量、访问IP地址信息
  * 【输出】：URL、cmsType(video/article)、cmsId(编号)、流量、ip、城市信息、访问时间、天
  */
object SecondCleanApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("SecondCleanApp").master("local[2]").getOrCreate()
    val accessRDD=spark.sparkContext.textFile("")
    import spark.implicits._
    val accessDF=accessRDD.map(x=>{x}).toDF()
    //控制文件输出的大小： coalesce方法
    accessDF.coalesce(1).write.format("parquet").partitionBy("day").mode(SaveMode.Overwrite).save("D:\\clean.log")
  }
}
