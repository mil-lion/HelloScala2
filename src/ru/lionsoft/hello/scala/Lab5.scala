package ru.lionsoft.hello.scala

object Lab5 {

  // ************** String Helper **********
  implicit class StringHelper(str: String) {
    def increment: String = str.map(c => (c + 1).toChar)
  }

  // ************ Matrix Helper ***********
  implicit class MatrixHelper(matr: Array[Array[Int]]) {
    def mkTable: String =
      (for (vec <- matr) yield vec.mkString("  [ ", ", ", " ]")).mkString("[\n", ",\n", "\n]")
  }


  def main(args: Array[String]): Unit = {
    // ************ String Increment ************
    println("123") // 123
    println("123".increment) // 234

    // ************* Matrix Helper ***********
    val matrix: Array[Array[Int]] = Array(Array(1, 2, 3), Array(4, 5, 6, 8, 9), Array(10, 11))
    println(s"matrix = ${matrix.mkTable}")
  }
}
