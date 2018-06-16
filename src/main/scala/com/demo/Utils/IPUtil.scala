package com.demo.Utils

import com.ggstar.util.ip.IpHelper

/**
  * IP转换省份
  */
object IPUtil {
  def parse(ip:String):String={
    IpHelper.findRegionByIp(ip)
  }
  def main(args: Array[String]): Unit = {
    println(IpHelper.findRegionByIp("39.105.95.154"))
  }
}
