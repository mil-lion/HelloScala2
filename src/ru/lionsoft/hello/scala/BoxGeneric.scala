package ru.lionsoft.hello.scala

/**
 * Класс описывающий коробку
 * (Конструктор коробки заданного размера)
 * @param w ширина коробки
 * @param h высота коробки
 * @param l длина коробки
 */
class BoxGeneric[T : Numeric](private var w: T,
                              private var h: T,
                              private var l: T)(implicit num: Numeric[T])
  extends Serializable with Ordered[BoxGeneric[T]]
{
  // Default Constructor Body
  if (num.toDouble(w) <= 0) throw new IllegalArgumentException("width <= 0")
  if (num.toDouble(h) <= 0) throw new IllegalArgumentException("height <= 0")
  if (num.toDouble(l) <= 0) throw new IllegalArgumentException("length <= 0")

  /**
   * Конструктор кубической коробки
   * @param size размер коробки
   */
//  def this(size: T) = {
//    this(size, size, size)
//  }

  /**
   * Конструктор коробки стандартного типоразмера
   * @param typeSize стандартный типоразмер коробки
   */
//  def this(typeSize: Char) = {
//    this() // !!!! Mandatory !!!!
//    typeSize match {
//      case 'S' => w = 1; h = 2; l = 3;
//      case 'M' => w = 4; h = 5; l = 6;
//      case 'L' => w = 7; h = 8; l = 9;
//      case 'X' => w = 10; h = 11; l = 12;
//    }
//  }

  // Getters & Setters
  def width = w
  def width_= (width: T) = {
    if (num.toDouble(width) <= 0) throw new IllegalArgumentException("width <= 0")
    w = width
  }

  def height = h
  def height_= (height: T) = {
    if (num.toDouble(height) <= 0) throw new IllegalArgumentException("height <= 0")
    h = height
  }

  def length = l
  def length_= (length: T) = {
    if (num.toDouble(length) <= 0) throw new IllegalArgumentException("length <= 0")
    l = length
  }

  def apply(): (T, T, T) = (w, h, l)
  def update(value: (T, T, T)): Unit = {
    width = value._1
    height = value._2
    length = value._3
  }

  // Cast to String
  override def toString: String = s"Box{width=$w, height=$h, length=$l}"

  // Equals & HashCode
  def canEqual(other: Any): Boolean = other.isInstanceOf[BoxGeneric[T]]

  override def equals(other: Any): Boolean = other match {
    case that: BoxGeneric[T] =>
      (that canEqual this) &&
        hashCode == that.hashCode &&
        w == that.w &&
        h == that.h &&
        l == that.l
    case _ => false
  }

  override def hashCode(): Int =
    Seq(w, h, l).map(_.hashCode()).foldLeft(3)((h, x) => 31 * h + x)

  // Pseudo Properties
  /**
   * Получить периметр коробки
   * @return
   */
  def perimeter: Double = Box.perimeter(num.toDouble(w), num.toDouble(h), num.toDouble(l))
  def squareSurface: Double = Box.squareSurface(num.toDouble(w), num.toDouble(h), num.toDouble(l))
  def volume: Double = Box.volume(num.toDouble(w), num.toDouble(h), num.toDouble(l))

  // Methods
  def open = println("Открыли коробку!")
  def close = println("Закрыли коробку!")

  // Compare by Volume
  override def compare(that: BoxGeneric[T]): Int = {
    if (this eq that) return 0
    if (that == null) return 1
    volume.compare(that.volume)
  }
}

