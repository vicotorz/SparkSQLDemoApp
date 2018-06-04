package com.demo.spark

import java.sql.DriverManager

/**
  * Created by dell on 2018/6/4.
  */
object SparkSQLThriftServerApp {
  def main(args: Array[String]): Unit = {
    Class.forName("org.apache.hive.jdbc.HiveDriver")
    val conn = DriverManager.getConnection("jdbc:hive2://39.105.95.154:14000", "root@iz2zef94dnmkl8kf3l63r9z", "6222771zhanG")
    val pstmt = conn.prepareStatement("select empno,ename,sal from emp")
    val rs = pstmt.executeQuery()
    while (rs.next()) {
      println("empno:" + rs.getInt("empno") + "," + "ename:" + rs.getString("ename"))
    }
    rs.close()
    pstmt.close()
    conn.close()
  }

}
