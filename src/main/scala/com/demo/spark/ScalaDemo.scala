package com.demo.spark

/**
  * scala 学习入门
  */
object ScalaDemo {

  def test(str: String): String = {
    return "hello " + str
  }

  //for语句
  def testfor() {
    for (i <- 1 to 10) {
      println(i)
    }

    for (y <- 1 until 10) {
      print(y + " ")
    }
    println()
    for(z<-1 to 100 if z%2==0){
      print(z+" ")
    }
  }

  //多个参数
  def testCan(num: Int, str: String) {
    println(num + "," + str)
  }

  //可变参数
  def testCan2(num: Int*): Unit = {
    println(num)
  }

  def testArray(): Unit = {
    var array = new Array[Int](3)
    array = Array(2, 3, 4)
    for (a <- array) {
      println(a)
    }

    var res = Array.tabulate(3)(a => a + 6)
    println(res(1))
  }

  def testList(): Unit = {
    var x = List(1, 2, 3, 4)
    var y = Set(1, 3, 5, 7)
    var z = Map("one" -> 1, "two" -> 2, "three" -> 3)
    println(x)
    println(y)
    println(z)
    println(x.map(2*_))

  }

  //模式匹配
  def testMatch(name: String): String = {
    name match {
      case "vic" => "victor"
      case "jim" => "jimmy"
      case _ => "none"
    }
  }

  //样例类
  case class Person(name: String, age: Int)

  def add(x: Int)(y: Int) = x + y

  //打印所有可变参数
  def printEveryOne(str: String*): Unit = {
    str.foreach(x => println(x))
  }

  //参数中的默认值
  def defaultCan(str: String = "defalut value"): String = {
     return str
  }

  //测试if
  def testIf(num:Int): Int={
    val a = if(num>10) 100 else 20
    return a
  }

  //多个参数
  def testMutiCan():Int ={
    val (n,r)=(20,30)
    return n
  }

  def main(args: Array[String]): Unit = {
    //println(test("scala"))
    testfor()
    //testCan(1, "sds")
    //testCan2(2,3,4)
    //testArray()
    //testList()
    //println(testMatch("vic"))
    //val p = Person("vic",20)
    //println(p)
    //println(add(1)(2))
    //printEveryOne("huawei", "xiaomi")
    //println(defaultCan())
    //print(testIf(90))
    //print(testMutiCan())
  }

}
