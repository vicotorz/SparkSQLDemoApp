package com.demo.sparkapp


import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}


/**
  *
  */
object AccessConvertUtil {
  //定义输出字段
  val struct = StructType(
    Array(
      StructField("url", StringType),
      StructField("cmsType", StringType),
      StructField("cmsId", LongType),
      StructField("traffic", LongType),
      StructField("ip", StringType),
      StructField("city", StringType),
      StructField("time", StringType),
      StructField("day", StringType)
    )
  )

  def parseLog(log: String) = {
    val splits = log.split("\t")
    val time = splits(0)
    val url = splits(1)
    val traffic = splits(2).toLong
    val ip = splits(3)

    val domain = "http://www.imooc.com"
    val cms = url.substring(url.indexOf(domain) + domain.length)
    val cmsTypeId = cms.split("/")

    val cmsType = if (cmsTypeId.length > 1) cmsTypeId(0) else " "
    val cmsId = if (cmsTypeId.length > 1) cmsTypeId(0) else " "
    val day = time.substring(0, 10).replaceAll("", "|")

    val city = IPUtils.parse(ip)//IpUtils

    Row(url, cmsType, cmsId, traffic, ip, city, time, day)
  }


}
