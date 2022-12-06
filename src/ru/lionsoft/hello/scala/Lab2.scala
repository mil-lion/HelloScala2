package ru.lionsoft.hello.scala

import java.math.BigInteger
import scala.util.Random

object Lab2 {

  def main(args: Array[String]): Unit = {
    val i1 = 12345L
    val i2 = 54321L
    val i3 = i1 + i2
    println(i3)
    println(i3.getClass)

    // Java Class
    val b1 = new BigInteger("123456789")
    val b2 = new BigInteger("9876543210")
//    val b3 = b1 + b2 // error
    val b3 = b1.add(b2) // b1 + b2
    println(b3)
    println(b3.getClass)

    // Scala Type
    val sb1: BigInt = 123_456_789      // int literal
//    val sb2: BigInt = 9_876_543_210L // long literal
    val sb2: BigInt = BigInt("9876543210") // BigInt > Long
    val sb3 = sb1 + sb2
    println(s"sb3 = $sb3, sb3.class = ${sb3.getClass}")
    val sb4 = sb1.+(sb2)
    println(s"sb4 = $sb4, sb4.class = ${sb4.getClass}")

    // ============== ветвления =============
    if (b1 != b2) println("b1 != b2") // b1 == b2 <-> b1.equals(b2)

    if (sb1 == sb2)
      println("sb1 == sb2")
    else
      println("sb1 != sb2")

    // ternary operator: expr ? is_true : is_false
    println(if (b1 == b2) "b1 == b2" else "b1 != b2")

    // ============ switch ================
    val r = new Random()
    val x = r.nextInt(5)
    println(x)

    if (x == 0) println("zero")
    else if (x == 1) println("one")
    else if (x == 2 || x == 3) println("two or three")
    else if (x == 4) println("four")
    else println("more four")

    println(if (x == 0) "zero"
      else if (x == 1) "one"
      else if (x == 2 || x == 3) "two or three"
      else if (x == 4) "four"
      else "more four"
    )

    x match {
      case 0 => println("zero")
      case 1 => println("one")
      case 2 | 3 => println("two or three")
      case 4 => println("four")
      case _ => println("more four") // default
    }

    println(x match {
        case 0 => "zero"
        case 1 => "one"
        case 2 | 3 => "two or three"
        case 4 => "four"
        case _ => "more four" // default
      }
    )

    // =============== loop ==============
    // Java Style
    println("##### while #####")
    var i = 0 // define variable
    while (i < 5) {
      println(i)
      i += 1 // i = i + 1
    }

    println("##### do-while #####")
    i = 5 // use variable
    do {
      println(i)
      i -= 1 // i = i - 1
    } while (i > 0)

    // Scala Style
    println("##### for #####")
    for (j <- 0 to 5) println(j)     // [0..5]
    for (j <- 0 until 5) println(j)  // [0..5)
    for (j <- 0.to(5)) println(j)     // [0..5]
    for (j <- 0.to(5, 2)) println(j)  // [0..5] step 2
    for (j <- 6 until (0, -1)) println(j)  // [6..0) step -1

    // for(m..) { for(n..) {...} }
    for (m <- 0 to 5; n <- 0 to 4) println(s"[$m:$n]")

    println("##### for-each #####")
    val dat = Array(1, 5, 3, 7, 8, 10, 12)
    // Java Style
    for (z <- dat) println(z)
    // Scala Style
    dat.foreach(z => println(z)) // anonymous function
    dat.foreach(println(_))
    dat.foreach(println) // reference on method

    // ############### operation #############
    val a: Byte = 127 // int literal
    val b: Byte = 2
    val c: Byte = (a + b).toByte    // byte + byte = int cast to byte
    val l: Long = 1_234_567_890_123_456L // long literal
    val ii: Int = (c + l).toInt     // byte + long = long cast to int
    val f: Float = 0.5f             // float literal
    val d: Double = 1.33            // double literal
    val f2: Float = l + f           // long + float = float
    val d2: Double = l + d          // long + double = double
    val f3: Float = (l + d).toFloat // long + double = double cast to float

    println(10) // 10 dec
//    println(010) // 8  oct -- Octal literals syntax has been disabled since Scala 2.11
    println(0x10) // 16 hex - 0001_0000
//    println(0b10) // 2  bin --- нет в Scala

    println("010".toInt)

    // ################## match ##############
    val rnd = math.random()
    val obj: Any = if (rnd > 0.3) 111 else if (rnd > 0.6) "one" else 1.5
    println(s"obj = $obj, obj.class = ${obj.getClass}")

    // Java Style
    if (obj.isInstanceOf[String]) {
      val str = obj.asInstanceOf[String] // Any cast to String
      println(s"str = $str is String, str.length = ${str.length}")
    } else if (obj.isInstanceOf[Int]) {
      val num = obj.asInstanceOf[Int]    // Any cast to Int
      println(s"num = $num is Int, hex = 0x${Integer.toHexString(num)}")
    } else {
      println("???")
    }

    // Scala Style
    obj match {
      case str: String => println(s"str = $str is String, str.length = ${str.length}")
      case num: Int    => println(s"num = $num is Int, hex = 0x${Integer.toHexString(num)}")
      case _           => println("???") // default
    }

    println(obj match {
        case str: String => s"str = $str is String, str.length = ${str.length}"
        case num: Int    => s"num = $num is Int, hex = 0x${Integer.toHexString(num)}"
        case _           => "???" // default
      })

    // ############# Exception ##############
    try {
      val m = (math.random() + 0.5).toInt
      val n = 5
      val k = n / m
      println(k)
      val nnn = 555 //null
      println(nnn.hashCode)
//      if (n == 5) throw new IllegalArgumentException("n != 5")
      if (n == 5) throw new MyException("Ошибка аргумента: должно быть n != 5", 123)
    } catch {
      case ex: ArithmeticException => System.err.println(s"Ошибка: Деление на ноль (${ex.getMessage})")
//      case ex: NullPointerException     => System.err.println(s"Ошибка Runtime: ${ex.getMessage}")
//      case ex: IllegalArgumentException => System.err.println(s"Ошибка Runtime: ${ex.getMessage}")
      case ex @ (_: NullPointerException | _: IllegalArgumentException) => System.err.println(s"Ошибка Runtime: ${ex.getMessage}")
      case ex: MyException => System.err.println(s"Моя Ошибка: ${ex.getMessage}, code: ${ex.code}")
      case ex: Exception => System.err.println(s"Ошибка: ${ex.getMessage}"); throw ex // Other exception
    } finally println("Ok")
    println("Exit!")
  }
}

class MyException(msg: String, val code: Int) extends Exception(msg)