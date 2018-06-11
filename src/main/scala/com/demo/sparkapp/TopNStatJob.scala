package com.demo.sparkapp

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

import scala.collection.mutable.ListBuffer

/**
  * 统计前N个
  *
  * 基本流程：
  * （1）
  * （2）
  */
object TopNStatJob {

  //统计视频前N个
  def VideoAccessTopNStat(spark:SparkSession,accessDF:DataFrame,day:String): Unit ={
    /*
    * (1) 使用DataFrame进行统计
    * */
    import spark.implicits._
    val accessTop = accessDF.filter($"day"==="20170511" && $"cmsType"==="video")
      .groupBy("day","cmsId").agg(count("cmsId").as("times"))
      .orderBy($"times".desc)
    accessTop.show(false)

    /**
      * (2)使用SQL进行统计
      */
    accessDF.createOrReplaceTempView("access_logs")
    val videoAccessTopDF = spark.sql("select day,cmsId,count(1) as times from access_logs where day='20170511' and cmsType='viedo' group by day,cmsId order by times desc")
    videoAccessTopDF.show(false)

    //处理每一个DataFrame，返回一个ListBuffer
    //处理每一个Listuffer中的内容
    videoAccessTopDF.foreachPartition(partitionOfRecords =>{
      val list =new ListBuffer[VideoAccessTopNStat]

      //拼接list
      partitionOfRecords.foreach(info =>{
        val day =info.getAs[String]("day")
        val cmsId =info.getAs[Long]("cmsId")
        val times=info.getAs[Long]("times")

        list.append(VideoAccessTopNStat(day,cmsId,times))
      })

      //存入mysql中
    })
  }

  case class VideoAccessTopNStat(day:String,cmsId:Long,times:Long)

  def main(args: Array[String]): Unit = {
    val spark=SparkSession.builder().appName("TopNStatJob").master("local[2]")
      .config("spark.sql.sources.partitionColumnTypeInference.enabled","false")
      .getOrCreate()

    val accessDF = spark.read.format("parquet").load("D:")
    val day="20180507"

    VideoAccessTopNStat(spark,accessDF,day)

    spark.stop()
  }


}
