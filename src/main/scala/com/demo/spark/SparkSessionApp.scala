package com.demo.spark

import org.apache.spark.sql.SparkSession
/**
  * spark2.x sparkSession的使用
  */
object SparkSessionApp {
  def main(args: Array[String]): Unit = {

    val path="D:\\BaiduYunDownload\\以慕课网日志分析为例 进入大数据 Spark SQL 的世界\\data\\test.json"
    val spark = SparkSession.builder().appName("SparkSessionApp")
      .master("local[2]").getOrCreate()

    val people = spark.read.json(path)
    people.show()

    spark.stop()
  }
}
