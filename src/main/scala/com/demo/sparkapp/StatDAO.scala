package com.demo.sparkapp

import java.sql.{Connection, PreparedStatement}

import com.demo.Utils.MySQLUtil
import com.demo.sparkapp.TopNStatJob.VideoAccessTopNStat

import scala.collection.mutable.ListBuffer

/**
  * Created by dell on 2018/6/12.
  */
object StatDAO {
  //将List中的内容存进数据库
  def insertDayVideoAccessTopN(list: ListBuffer[VideoAccessTopNStat]) = {
    var connection: Connection = null
    var pstmt: PreparedStatement = null
    try {
      connection = MySQLUtil.getConnection()
      connection.setAutoCommit(false)
      val sql = "insert into day_video_access_topn_stat(day,cms_id,times) values (?,?,?)"
      pstmt = connection.prepareStatement(sql)
      //拼接sql
      for (element <- list) {
        pstmt.setString(1, element.day)
        pstmt.setLong(2, element.cmsId)
        pstmt.setLong(3, element.times)
        pstmt.addBatch()
      }

      pstmt.executeBatch() //批量处理
      connection.commit()

    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      MySQLUtil.release(connection, pstmt)
    }
  }

  //删除指定日期的数据
  def deleteData(day: String) = {
    //清空所有表各种的数据
    val tables = Array("day_video_access_topn_stat",
      "day_video_city_access_topn_stat",
      "day_video_traffics_topn_stat")
    var connection: Connection = null
    var pstmt: PreparedStatement = null

    for (table <- tables) {
      val deleteSQL = s"delete from $table where day=?"
      connection.prepareStatement(deleteSQL)
      pstmt.setString(1, day)
      pstmt.executeUpdate()
    }
  }

}
