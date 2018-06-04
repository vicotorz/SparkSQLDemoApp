package com.demo.spark

import org.apache.spark.sql.SparkSession

/**
  * RDD 转化为 DataFrame
  */
object DataFrameRDDApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrameApp").master("local[2]").getOrCreate()
    //RDD 转换 DataFrame
    val rdd = spark.sparkContext.textFile("files:///39.105.95.154/root/data/student.data")

    //需要导入隐式转换
    import spark.implicits._
    val studentDataFrame = rdd.map(_.split(",")).map(line => Student(line(0).toInt, line(1), line(2), line(3))).toDF()

    //(1)dataFranme方式编程
    studentDataFrame.show(30)
    //studentDataFrame.filter(infoDF.col("age") > 30).show

    //（2）SparkSQL方式编程
    //infoDF.createOrReplaceTempView("infos")
    //spark.sql("select * from infos where age>30").show()
    //spark.stop()
  }

  case class Student(id: Int, name: String, phone: String, email: String)

}
