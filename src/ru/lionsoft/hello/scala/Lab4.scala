package ru.lionsoft.hello.scala

import javax.management.Notification
import scala.util.Random

object Lab4 {

  def main(args: Array[String]): Unit = {
    val m1 = new Matrix(3, 5)
    println(s"m1[${m1.rows}x${m1.cols}] = $m1")

    val x = m1(2, 3) // apply
    println(x)
    m1(2, 3) = 10     // update
    println(m1(2, 3)) // apply
    println(s"m1[${m1.rows}x${m1.cols}] = $m1")
//    println(m1(3, 3)) // error: ArrayIndexOutOfBoundsException: row

    m1.fill(1)
    println(m1(2, 3)) // 1
    println(s"m1[${m1.rows}x${m1.cols}] = $m1")

    m1.fillRandom()
    println(s"m1[${m1.rows}x${m1.cols}] = $m1")

    // Use Function

    m1.fill(() => 1) // value
    println(s"m1[${m1.rows}x${m1.cols}] = $m1")

    val r = new Random

    m1.fill(() => r.nextInt(100)) // random 0..100
    println(s"m1[${m1.rows}x${m1.cols}] = $m1")

    m1.fill(() => r.nextInt(200) - 100) // random -100..100
    println(s"m1[${m1.rows}x${m1.cols}] = $m1")

    var counter = 0
    m1.fill(() => {counter += 1; counter}) // 1 2 3 4 5
    println(s"m1[${m1.rows}x${m1.cols}] = $m1")

    val m2 = new Matrix(3, 5)
    m2.fill((row, col) => row + col) // row+col
    println(s"m2[${m2.rows}x${m2.cols}] = $m2")

    val m3 = m1 + m2
    println(s"m3[${m3.rows}x${m3.cols}] = $m3")

    val m4 = m1 - m2
    println(s"m4[${m4.rows}x${m4.cols}] = $m4")

    val m5 = m1.combine(m2, (m1, m2) => m1 * m2)
    println(s"m5[${m5.rows}x${m5.cols}] = $m5")

    val m6 = m1 * 5
    println(s"m6[${m6.rows}x${m6.cols}] = $m6")

    val m7 = Matrix(5, 7)()
    println(s"m7[${m7.rows}x${m7.cols}] = $m7")

    val m8 = Matrix(2, 2)(2, 4, 8, 10)
    println(s"m8[${m8.rows}x${m8.cols}] = $m8")

    // ############## Tuple ##############
    var ingredient: Tuple2[String, Int] = ("Sugar", 25) // Tuple2[String, Int]
    println(ingredient._1) // Sugar
    println(ingredient._2) // 25

    ingredient = ("Сахар", 5)

    // распаковка кортежа
    val (name, quantity) = ingredient
    println(name) // Сахар
    println(quantity) // 5

//    ingredient._2 = 15 // immutable

    val planetDistanceFromSun = List(("Mercury", 57.9), ("Venus", 108.2), ("Earth", 149.6), ("Mars", 227.9), ("Jupiter", 778.3)) // immutable
    println(planetDistanceFromSun(1))
//    planetDistanceFromSun(1) = ("Venus", 10.2) error
    planetDistanceFromSun.foreach { tuple => {
        tuple match {
          case ("Mercury", distance) => println(s"Mercury is $distance millions km far from Sun")
          case p if (p._1 == "Venus") => println(s"Venus is ${p._2} millions km far from Sun")
          case p if (p._1 == "Earth") => println(s"Blue planet is ${p._2} millions km far from Sun")
          case _ => println("Too far.... ")
        }
      }
    }

    val numPairs = List((2, 5), (3, -7), (20, 56))
    for ((a, b) <- numPairs) {
      println(a * b)
    }

    println("####")
    var planets = planetDistanceFromSun :+ ("Земля", 123) // appended
    planets.foreach(println)

    println("####")
    planets = ("Венера", 321) +: planets // prepended
    planets.foreach(println)

    println("####")
    planets = planets ++ planetDistanceFromSun // concat
    planets.foreach(println)

    // :++ alas appendedAll
    // ++: alias prependedAll

    // ############## Match ################
    abstract class Notification
    case class Email(sender: String, subject: String, body: String) extends Notification
    case class SMS(caller: String, message: String) extends Notification
    case class VoiceRecording(contactName: String, link: String) extends Notification

    val someSms = SMS("12345", "Are you there?")
    val someVoiceRecording = VoiceRecording("Tom", "http://voicerecording.org/id/123")

    def showNotification(notification: Notification): String =
      notification match {
        case Email(from, title, _) => s"You got an email from ${from} with title: ${title}"
        case SMS(number, message) => s"You got an SMS from ${number}! Message: ${message}"
        case VoiceRecording(name, url) => s"You receive a Voice Recording from ${name}! Click the link to hear it: ${url}"
      }

    println(showNotification(someSms)) // You got an SMS from 12345! Message: Are you there?
    println(showNotification(someVoiceRecording)) // You receive a Voice Recording from Tom! Click the link to hear it: http://voicerecording.org/id/123

//    val obj: Any = 1
//    val obj: Any = 1.5
//    val obj: Any = "1.5"
    val obj: Any = Box(1, 2, 3) // Box.apply
    println(obj match {
      case str: String => s"String = $str"
      case n: Int => s"Integer = $n"
      case x: Double => s"Double = $x"
//      case b: Box => b.toString
      case Box(w, h, l) => s"Корбка ${w}x${h}x${l}" // Box.unapply - развертывание объекта на параметры
      case _ => "???"
    })

    val box = Box(1, 2, 3)
    println(box)

    val (w, h, l) = box() // apply
    println(s"Box ${w}x${h}x${l}")

    box() = (3, 5, 9) // update
    println(box)

    // ############ Ограждение примеров #############

    def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String =
      notification match {
        case Email(email, _, _) if importantPeopleInfo contains email => "You got an email from special someone!"
        case SMS(number, _) if importantPeopleInfo.contains(number) => "You got an SMS from special someone!"
        case that => showNotification(that) // любой другой случай
      }

    val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")

    val importantEmail = Email("jenny@gmail.com", "Drink tonight?", "I'm free after 5!")
    val importantSms = SMS("867-5309", "I'm here! Where are you?")

    println(showImportantNotification(someSms, importantPeopleInfo)) // You got an SMS from 12345! Message: Are you there?
    println(showImportantNotification(someVoiceRecording, importantPeopleInfo)) // You receive a Voice Recording from Tom! Click the link to hear it: http://voicerecording.org/id/123
    println(showImportantNotification(importantEmail, importantPeopleInfo)) // You got an email from special someone!
    println(showImportantNotification(importantSms, importantPeopleInfo)) // You got an SMS from special someone!

    // ################# Generic Type ##############

    val gm1 = new MatrixGeneric[Double](3, 5)
    println(gm1(2, 3))
    gm1(2, 3) = 1.5
    println(gm1(2, 3))
    println(s"gm1 = $gm1")

//    val gm2 = new MatrixGeneric[String](2, 2)
//    gm2(0, 0) = "one"
//    gm2(0, 1) = "two"
//    gm2(1, 0) = "three"
//    gm2(1, 1) = "four"
//    println(s"gm2 = $gm2")

    val gb1 = new BoxGeneric[Int](1, 2, 3)
    val gb2 = new BoxGeneric[Long](1, 2, 3)
    val gb3 = new BoxGeneric[Float](1.5f, 2.3f, 3.4f)
//    val gb4 = new BoxGeneric[String]("width", "height", "length")
    println(gb1)
    println(gb2)
    println(gb3)
//    println(gb4)

    // ################## for with if #############
    val planetNames = for (p <- planetDistanceFromSun if p._2 > 120) yield p._1
    planetNames.foreach(println)

    planetDistanceFromSun
      .filter(_._2 > 120) // (e) -> e._2 > 120
      .map(_._1)          // (e) -> e._1
      .foreach(println)
  }
}
