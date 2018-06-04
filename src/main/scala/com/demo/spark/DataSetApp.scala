package com.demo.spark

import org.apache.spark.sql.SparkSession

/**
  * Created by dell on 2018/6/4.
  */
object DataSetApp {
  def main(args: Array[String]): Unit = {
    val path="D:\\BaiduYunDownload\\以慕课网日志分析为例 进入大数据 Spark SQL 的世界\\data\\sales.csv"
    val spark = SparkSession.builder().appName("DataSetApp").master("local[2]").getOrCreate()
    import spark.implicits._
    val dataframe = spark.read.option("header","true").option("inferSchema","true").csv(path)
    dataframe.show

    //dataFrame 转化 dataSet
    val dataset = dataframe.as[Sales]
    dataframe.select("amountPaid").show()

    dataset.map(line => line.itemId)
    spark.stop()
  }

  case class Sales(transactionId:Int,customerId:Int,itemId:Int,amountPaid:Double)

}
