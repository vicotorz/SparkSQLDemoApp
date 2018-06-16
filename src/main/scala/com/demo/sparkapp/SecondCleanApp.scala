package com.demo.sparkapp

import org.apache.spark.sql.SparkSession


/**
  * 信息转化
  * 【输入】：访问时间、访问URL、耗费的流量、访问IP地址信息
  * 【输出】：URL、cmsType(video/article)、cmsId(编号)、流量、ip、城市信息、访问时间、天
  */
object SecondCleanApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("SecondCleanApp").master("local[2]").config("spark.sql.sources.partitionColumnTypeInference.enabled", "false").getOrCreate()
    val accessRDD = spark.sparkContext.textFile("D:\\\\clean_first.log")
    val accessDF = spark.createDataFrame(accessRDD.map(
      x => AccessConvertUtil.parseLog(x)
    ), AccessConvertUtil.struct)
    accessDF.printSchema()
    accessDF.show(false)
    //控制文件输出的大小： coalesce方法
    //accessDF.coalesce(1).write.format("parquet").partitionBy("day").mode(SaveMode.Overwrite).save("D:\\\\clean.log")
    spark.stop()
  }
}
