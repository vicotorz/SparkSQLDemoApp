package com.demo.sparkapp

import java.util.{Date, Locale}

import org.apache.commons.lang3.time.FastDateFormat

/**
  * 时间转换
  */
object DateUtil {
  val YYYYMMDDHHMM_TIME_FORMAT=FastDateFormat.getInstance("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH)
  val TARGET_FORMAT=FastDateFormat.getInstance("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH)

  def parse(time:String)={
    TARGET_FORMAT.format(new Date(getTime(time)))
  }

  def getTime(time:String):Long={
    try{
      YYYYMMDDHHMM_TIME_FORMAT.parse(time.substring(time.indexOf("[")+1,time.lastIndexOf("]"))).getTime
    }catch {
      case e:Exception=>{
        0L
      }
    }

  }
}
