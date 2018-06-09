package com.demo.spark

import scala.util.matching.Regex

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
    for (z <- 1 to 100 if z % 2 == 0) {
      print(z + " ")
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
    println(x.map(2 * _))
    val User = ("username", "password") //元组
    println(User)
    println(User._1)
    println(User._2)
  }

  //集合操作
  def testOperation = {
    var list=List(3,4,5,6)
    list.map(4*_)//map操作
    list.foreach(5*_)//遍历
    list.filter(x=>x%2==0)//分开
    var a=List(1,3,5)
    var b=List(2,4,6)
    var c=a.zip(b)//zip一一对应整合
    println(c)
    println(list.partition(_<3))
    val l = List(List("a","b"),List("c","d")).flatten//flattern
    println(l)
    println(list.flatMap(x=>x.toString))
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
  def testIf(num: Int): Int = {
    val a = if (num > 10) 100 else 20
    return a
  }

  //多个参数
  def testMutiCan(): Int = {
    val (n, r) = (20, 30)
    return n
  }

  //类
  abstract class Human {
    def say(): Unit = {
      println("say hello")
    }
  }

  class fater extends Human {
    var name: String = "father"
    var age: Int = 60
    private var secret: String = "secret_father"

    override def say(): Unit = {
      println(name + "," + age + "," + secret)
    }
  }

  class son extends fater {
    name = "son"
    age = 20
    private var secret: String = "secret_son"

    override def say(): Unit = {
      println(name + "," + age + "," + secret)
    }

    def apply() = {
      println("I am into Scala so much!")
    }

    def haveTry() = {
      println("Hava a try on apply!")
    }
  }

  object ApplyTest {
    def apply() = {
      println("I am also into Scala so much!")
      new son
    }
  }

  trait xx{}

  //隐式转换，隐式参数，隐式类
  class A{}

  class B(a:A){//隐式扩展A
    def func: Unit ={
      println("hello scala from B")
    }
  }

  object B{
    implicit def B(a:A) = new B(a)
    val a=new A
    a.func
  }

  //隐式参数
  object C{
    def testParam(implicit name:String): Unit ={
      println(name)
    }
    implicit val name="default name"
    testParam
  }

  implicit class D(x:Int){
    def add(a:Int):Int=a+1
  }

  def main(args: Array[String]): Unit = {
    //println(test("scala"))
    //testfor()
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
    //var a = ApplyTest()
    //println(a.age)
    //a.say()
    //testOperation
    //B
    //C
    //println(1.add(1))

    //正则匹配
    val pattern = new Regex("abl[ae]\\d+")
    val str = "ablaw is able1 and cool"

    println((pattern findAllIn str).mkString(","))
  }

}
