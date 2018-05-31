package com.demo.spark

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Spark 1.x HiveContext
  */
object HiveContextApp {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()

    val sc = new SparkContext(sparkConf)
    val hiveContext = new HiveContext(sc)

    //处理,直接读取hive表中的数据
    hiveContext.table("emp").show()//emp数据表

    sc.stop()
  }
}
