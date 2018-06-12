package com.demo.sparkapp

import java.sql.{Connection, DriverManager, PreparedStatement}

/**
  * 控制Mysql连接，释放
  */
object MySQLUtil {
  def getConnection() = {
    DriverManager.getConnection("jdbc:mysql://localhost:3306/imooc_project?user=root&password=123")
  }

  def release(connection:Connection,pstmt:PreparedStatement) = {
    pstmt.close()
    connection.close()
  }
}
