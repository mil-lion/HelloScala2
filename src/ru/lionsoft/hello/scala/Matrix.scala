package ru.lionsoft.hello.scala

import scala.util.Random

class Matrix(val rows: Int, val cols: Int) {
  private val dat = new Array[Int](rows * cols)

  private def idx(row: Int, col: Int): Int = {
    if (row < 0 || row >= rows) throw new ArrayIndexOutOfBoundsException("row")
    if (col < 0 || col >= cols) throw new ArrayIndexOutOfBoundsException("col")
    row * cols + col
  }

  private def parseIdx(i: Int): (Int, Int) = (i / cols, i % cols) // Tuple2[Int, Int] (row, col)

  // get
  def apply(row: Int, col: Int): Int = dat(idx(row, col))
  // set
  def update(row: Int, col: Int, value: Int) = dat(idx(row, col)) = value

  /**
   * Заполнить матрицу значением
   * @param value значение для заполнения матрицы
   */
  def fill(value: Int) =
    for (i <- dat.indices)
      dat(i) = value

  /**
   * Заполнить матрицу случайными положительными значениями
   * @param bound максимальное число генерации
   */
  def fillRandomPos(bound: Int = 100) = {
    val r = new Random
    for (i <- dat.indices)
      dat(i) = r.nextInt(bound)
  }

  /**
   * Заполнить матрицу случайными значениями
   * @param bound максимальное число генерации
   */
  def fillRandom(bound: Int = 100) = {
    val r = new Random
    for (i <- dat.indices)
      dat(i) = r.nextInt(2 * bound) - bound
  }

  def fill(f: () => Int): Unit =
    for (i <- dat.indices)
      dat(i) = f()

  def fill(f: (Int, Int) => Int): Unit =
//    for (row <- 0 until rows; col <- 0 until cols)
//      dat(idx(row, col)) = f(row, col)
    for (i <- dat.indices) {
      val (row, col) = parseIdx(i)
      dat(i) = f(row, col)
    }

  def combine(that: Matrix, f: (Int, Int) => Int): Matrix = {
    if (rows != that.rows || cols != that.cols)
      throw new IllegalArgumentException("Матрицы должны быть одного размера")
    val newMatrix = new Matrix(rows, cols)
    for (i <- dat.indices)
      newMatrix.dat(i) = f(this.dat(i), that.dat(i))
    newMatrix
  }

//  def + (that: Matrix): Matrix = combine(that, (m1, m2) => m1 + m2)
//  def - (that: Matrix): Matrix = combine(that, (m1, m2) => m1 - m2)
  def + (that: Matrix): Matrix = combine(that, _ + _)
  def - (that: Matrix): Matrix = combine(that, _ - _)

  def map(f: (Int) => Int): Matrix = {
    val newMatrix = new Matrix(rows, cols)
    for (i <- dat.indices)
      newMatrix.dat(i) = f(dat(i))
    newMatrix
  }

  def * (value: Int): Matrix = map(_ * value)
  def / (value: Int): Matrix = map(_ / value)
  def + (value: Int): Matrix = map(_ + value)
  def - (value: Int): Matrix = map(_ - value)
  def % (value: Int): Matrix = map(_ % value)

  // Cast to String
  override def toString: String = {//dat.mkString("[", ",", "]")
    val sb = new StringBuilder("[")
    for (row <- 0 until rows; col <- 0 until cols)
      sb.append(if (col == 0) '\n' else ',').append(dat(idx(row, col)))
    sb.append("\n]").toString
  }

  // Equals & HashCode

  def canEqual(other: Any): Boolean = other.isInstanceOf[Matrix]

  override def equals(other: Any): Boolean = other match {
    case that: Matrix =>
      (that canEqual this) &&
        (dat sameElements that.dat) &&
        rows == that.rows &&
        cols == that.cols
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(dat, rows, cols)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

object Matrix {
  // fabric method
//  def apply(rows: Int, cols: Int): Matrix = new Matrix(rows, cols)

  def apply(rows: Int, cols: Int)(values: Int*): Matrix = {
    val newMatrix = new Matrix(rows, cols)
    // copy values
    if (values != null)
      values.copyToArray(newMatrix.dat, 0, math.min(rows * cols, values.length))
    newMatrix
  }
}
