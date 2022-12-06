package ru.lionsoft.hello.scala

/**
 * Первый объект на языке Scala
 * @author Igor Morenko
 * @version 1.0
 */
object Lab1 {

  /**
   * Главный метод приложения
   * @param args аргументы командной строки
   */
  def main(args: Array[String]) = {
    // Вывод сообщения на экран
    println("Hello Scala!")
    /*
     * Многострочный
     * комментарий
     */
    println(1 + 3) // 4
    println("Hello, " + "World!") // Hello, World!

    // ============= Value ===========
    val x = 1 + 1 // Int
    println(x) // 2
    //    x = 3 // error!
    val x1: Long = 1 + 1
    val x2 = 1L + 1 // Long

    // ============= Variable =========
    var y = 1 + 1
    y = 3
    println(y * y) // 9

    // ======== Block =========
    println({
      val x = 1 + 3
      x + y
    }) // 7
    println(x) // 2

    val res = {
      val x = 1 + 3
      x + y
    }
    println(res) // 7

    // ======== Function =======

    val addOne = (x: Int) => x + 1 // reference on anonymous function
    println(addOne(1)) // 2

    var add = addOne // Int => Int
    println(add(2)) // 3
    add = (x: Int) => x + 2 // Int => Int
    println(add(2)) // 4

    val add2 = (x: Int, y: Int) => x + y // (Int, Int) => Int
    println(add2(10, 20))

    val add3 = (a: Int, b: Long, c: Byte) => a + b + c // (Int, Long, Byte) => Long
    println(add3(123, 2, 3)) // 128
    println(add3.apply(123, 2, 3)) // 128

    // ============ Method ==============
    def addInt(x: Int, y: Int): Int = x + y

    println(addInt(11, 22)) // 33

    println(addThenMultiply(10, 20)(3)) // 90

    def суммаРазделитьНаРазницу(x: Int, y: Int)(a: Int, b: Int) = (x + y) / (a - b)

    println(суммаРазделитьНаРазницу(10, 20)(3, 4)) // -30

    def name: String = System.getProperty("user.name") // invoke Java Class method
    println("Hello " + name + "!")

    def getSquareString(input: Double): String = {
      //      val square = input * input
      def square(x: Double) = x * x

      square(input).toString + " m\u00B2"
    }

    println(getSquareString(2.5)) // 6.25 m2

    // =============== String Format ========
    println("Hello " + name + "!")
    println(s"Hello $name!")
    val age = 49.5
    println(f"Name: ${name}%10s, age: ${age}%07.2f") // Java String.format

    val s1 = "s1 = Hello \"Igor\"\n  from Scala!\nThis is string!\n"
    println(s1)

    val s2 =
      """s2 = Hello "Igor"
  from Scala!
This is string!
"""
    println(s2)

    val s3 =
      """s3 = Hello "Igor"
        |  from Scala!
        |This is string!
        |""".stripMargin
    println(s3)

    // ========= Class ==========
    val greeter = new Greeting("Hello, ", "!") // new instance of class Greeting
    greeter.greet("Scala Developer") // Hello, Scala Developer!

    // ============= Case Class =============
    val point = new Point(1, 2) // constructor
    val anotherPoint = Point(1, 2) // fabric method
    val yetAnotherPoint = Point.apply(2, 2) // fabric method

    if (point == anotherPoint) { // object equals
      println(point + " and " + anotherPoint + " are the same.")
    } else {
      println(point.toString + " and " + anotherPoint + " are different.")
    } // Point(1,2) and Point(1,2) are the same.

    if (point == yetAnotherPoint) {
      println(point + " and " + yetAnotherPoint + " are the same.")
    } else {
      println(point + " and " + yetAnotherPoint + " are different.")
    } // Point(1,2) and Point(2,2) are different.

    if (point eq anotherPoint) { // references equals
      println(point + " and " + anotherPoint + " instance are the same.")
    } else {
      println(point.toString + " and " + anotherPoint + " instance are different.")
    } // Point(1,2) and Point(1,2) instance are different.

    if (point eq yetAnotherPoint) {
      println(point + " and " + yetAnotherPoint + " instance are the same.")
    } else {
      println(point + " and " + yetAnotherPoint + " instance are different.")
    } // Point(1,2) and Point(2,2) instance are different.

    //    point.x = 3; // error!

    // ============= Object ==============
    val newId: Int = IdFactory.create()
    println(newId) // 1
    val newerId: Int = IdFactory.create
    println(newerId) // 2

    // ============= Trait (Особенность) =========
    var greeter2: Greeter = new DefaultGreeter
    greeter2.greet("Scala Developer") // Hello, Scala Developer!

    greeter2 = new CustomizableGreeter("How are you, ", "?")
    greeter2.greet("Scala Developer") // How are you, Scala Developer?

    // ============== Type =============
    val list: List[Any] = List(
      "a string", // строка java.lang.String
      732,        // целое число java.lang.Integer
      'c',        // символ java.lang.Character
      true,       // логическое значение java.lang.Boolean
      () => "анонимная функция возвращающая строку", // Lambda
      1.5         // вещественное число (с плавающей точкой) java.lang.Double
    )
    list.foreach(element => println(element)) // анонимная функция
    list.foreach(element => println(element.getClass))

    val xx: Long = 987654321 // int -> long
    val yy: Float = xx // 9.8765434E8 (заметьте, что некоторая точность теряется в этом случае.)
    val yy2: Double = xx // long -> double
    val face: Char = '©'
    val number: Int = face // 169 char -> int
    val zz: java.lang.Long = yy.toLong // float -> long
    println(s"xx = $xx, yy = $yy, face = $face, number = $number, zz = $zz")
    println("xx.class = " + xx.getClass)
    println("zz.class = " + zz.getClass)

    val b1: Byte = 1
    val b2: Byte = 2
    val b3: Byte = (b1 + b2).toByte // byte + byte = int -> byte

    val str1 = "12345 "
    val str2 = "54321a"
    val str1Num = str1.trim.toInt
    val str2Num = str2.toInt
    println(str1Num + str2Num)
  }

  // Method of object Lab1
  def addThenMultiply(x: Int, y:Int)(multiplier: Int) = (x + y) * multiplier

  // ============= Case Class (immutable) =============
  case class Point(x: Int, y: Int)
  case class Point3D(x: Int, y: Int, z: Int)

}

// ============= Class =============

class Greeting(/*Fields:*/prefix: String, suffix: String) { // default: private val
  // Constructor Body
  println("Created Instance of class Greeting!")

  // Method
  def greet(name: String) = println(prefix + name + suffix)
}

// ============= Object ==============
object IdFactory {
  private var counter = 0
  def create(): Int = {
    counter += 1
    counter
  }
}

// ============= Trait =========
trait Greeter {
  def greet(name: String): Unit =
    println("Hello, " + name + "!")
}

class DefaultGreeter extends Greeter

class CustomizableGreeter(prefix: String, suffix: String) extends Greeter {
  override def greet(name: String): Unit =
    println(prefix + name + suffix)
}
