package ru.lionsoft.hello.scala

object Lab3 {

  def main(args: Array[String]): Unit = {

    // ################ Class Point ################
    val p1 = new Point(2, 3)
    val p2: AnyRef = new Point(-5, 10)
    println(p1.x) // 2
    println(p1)   // Point(2, 3)
    println(p2.toString) // Point(-5, 10)

    p1.move(1, 2)
    println(p1) // Point(3, 5)
    println(p2) // Point(-5, 10)

    p1.x = 6
    println(p1) // Point(6, 5)

    val p3 = new Point
    val p4 = new Point(1)
    println(p3) // Point(0, 0)
    println(p4) // Point(1, 0)

    val p5 = new Point(_y = 2)
    println(p5) // Point(0, 2)

    p5.x = 200  // WARNING: Out of bounds
    p5.y = -128 // WARNING: Out of bounds

    // ################ Class Box ################

    def printBox(name: String, box: Box) = println(
      s"""
         |${name} = $box
         |${name}.perimeter     = ${box.perimeter} m
         |${name}.squareSurface = ${box.squareSurface} m\u00B2
         |${name}.volume        = ${box.volume} m\u00B3""".stripMargin)

    val b1 = new Box(1, 2, 3)
    printBox("b1", b1)
    println(b1.width)

    val b2 = new Box
    printBox("b2", b2)
    val b3 = new Box(l = 10)
    printBox("b3", b3)

    try {
      val b4 = new Box(-1, -2, 0) // error IllegalArgumentException: width <= 0
      printBox("b4", b4)
      b4.width = -5 // error IllegalArgumentException: width <= 0
    } catch { case ex: Exception => System.err.println(s"Error: ${ex.getMessage}") }

    val b5 = new Box(10)
    printBox("b5", b5)

    val b6 = new Box(w = 10)
    printBox("b6", b6)

    val b7 = new Box('S')
    printBox("b7", b7)

    // equals
    println(if (b1 == b2) "b1 == b2" else "b1 != b2") // b1 != b2
    println(if (b1 == b7) "b1 == b7" else "b1 != b7") // b1 == b7 (equals)
    println(if (b1 eq b7) "b1 eq b7" else "b1 not eq b7") // b1 not eq b7

    // hashCode
    println(s"b1.hashCode = ${b1.hashCode}")
    println(s"b2.hashCode = ${b2.hashCode}")
    println(s"b7.hashCode = ${b7.hashCode}")
    println(s"b7.## = ${b7.##}")

    // fabric method
    val b8 = Box(1, 2, 3)
    val b9 = Box.apply(1, 2, 3)
    printBox("b8", b8)

    val b10 = Box(Box.TYPE_SIZE_LARGE)
    printBox("b10", b10)

    // static function
    val vol1 = new Box(10, 15, 20).volume
    println(s"vol1(10, 15, 20) = $vol1")

    val vol2 = Box.volume(10, 15, 20)
    println(s"vol2(10, 15, 20) = $vol2")

    // ############### trait ##############
    println(if (b1.isInstanceOf[Serializable]) "Serializable" else "Not Serializable")

    val iter: Iterator[Int] = new IntIterator(5)
    println(iter.next) // 0
    println(iter.next) // 1
    println(iter.hasNext) // true

    println("###")
    val intList: List[Int] = List(1, 10, -5, 20, 13, 1, -19)
    intList.foreach(println)
    println("###")
    val sortedIntList = intList.sorted
    sortedIntList.foreach(println)

    println("###")
    val listBox = List(Box(1, 2, 3), Box('M'), Box('X'), new Box(10), Box(3, 2, 1))
    listBox.foreach(println)
    println("###")
    val sortedListBox = listBox.sorted
    sortedListBox.foreach(println)

    println(if (b1 < b2) "b1 < b2" else "b1 >= b2")

//    println("###")
//    val sortedListBox2 = listBox.sorted((b1, b2) => b1.width - b2.width)
//    sortedListBox2.foreach(println)
  }

  trait Iterator[A] {
    def hasNext: Boolean
    def next: A
  }

  class IntIterator(to: Int) extends Iterator[Int] {
    var counter = 0
    override def hasNext: Boolean = counter < to

    override def next: Int = {
      if (hasNext) {
        counter += 1
        counter - 1
      } else 0
    }
  }
}
