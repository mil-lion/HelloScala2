package ru.lionsoft.hello.scala

import scala.reflect.ClassTag

class MatrixGeneric[T : Numeric : ClassTag](val rows: Int, val cols: Int) {
  def size = rows * cols;
  private val dat: Array[T] = new Array[T](size)

  private def idx(row: Int, col: Int) = {
    if (row < 0 || row >= rows) throw new ArrayIndexOutOfBoundsException("row")
    if (col < 0 || col >= cols) throw new ArrayIndexOutOfBoundsException("col")
    row * cols + col
  }

  private def parseIdx(i: Int): (Int, Int) = (i / cols, i % cols) // Tuple2[Int, Int] (row, col)

  // get
  def apply(row: Int, col: Int): T = dat(idx(row, col))
  // set
  def update(row: Int, col: Int, value: T) = dat(idx(row, col)) = value

  // Cast to String
  override def toString: String = {//dat.mkString("[", ",", "]")
    val sb = new StringBuilder("[")
    for (row <- 0 until rows; col <- 0 until cols)
      sb.append(if (col == 0) "\n  " else ", ").append(dat(idx(row, col)))
    sb.append("\n]").toString
  }

  // Equals & HashCode

  def canEqual(other: Any): Boolean = other.isInstanceOf[MatrixGeneric[T]]

  override def equals(other: Any): Boolean = other match {
    case that: MatrixGeneric[T] =>
      (that canEqual this) &&
        (dat sameElements that.dat) &&
        rows == that.rows &&
        cols == that.cols
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(rows, cols).concat(dat)
    state.map(_.hashCode()).foldLeft(3)(31 * _ + _)
  }
}

object MatrixGeneric {
  // fabric methods
  def apply[T: Numeric : ClassTag](rows: Int, cols: Int)(values: T*): MatrixGeneric[T] = {
    val newMatrix = new MatrixGeneric[T](rows, cols)
    if (values != null)
      values.copyToArray(newMatrix.dat, 0, math.min(newMatrix.size, values.length))
    newMatrix
  }
  // развертывание
  def unapply[T : Numeric: ClassTag](m: MatrixGeneric[T]): Option[(Int, Int, Array[T])] =
    Option(m.rows, m.cols, m.dat.clone)
}
