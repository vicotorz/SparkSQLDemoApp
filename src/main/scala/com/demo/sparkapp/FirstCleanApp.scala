package com.demo.sparkapp

import org.apache.spark.sql.SparkSession


/**
  * 1.从原始数据中截取信息
  */
class FirstCleanApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("FirstCleanApp").master("local[2]").getOrCreate()
    val access = spark.sparkContext.textFile("")

    //开始处理
    access.map(line => {
      val splits = line.split(" ")
      val ip = splits(0)
      val time = splits(3) + " " + splits(4)

      val url = splits(11).replaceAll("\"", "")
      val traffic = splits(9)

      DateUtil.parse(time)+"\t"+url+"\t"+traffic

    }).take(10).foreach(println)
  }

}
