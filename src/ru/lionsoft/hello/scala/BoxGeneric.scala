package ru.lionsoft.hello.scala

/**
 * Класс описывающий коробку
 * (Конструктор коробки заданного размера)
 * @param w ширина коробки
 * @param h высота коробки
 * @param l длина коробки
 */
class BoxGeneric[T : Numeric](private var w: T = 1,
                              private var h: T = 1,
                              private var l: T = 1)
  extends Serializable //with Ordered[BoxGeneric[T]]
{
  // Default Constructor Body
//  if (w <= 0) throw new IllegalArgumentException("width <= 0")
//  if (h <= 0) throw new IllegalArgumentException("height <= 0")
//  if (l <= 0) throw new IllegalArgumentException("length <= 0")

  /**
   * Конструктор кубической коробки
   * @param size
   */
  def this(size: T) = this(size, size, size)

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
  def width_= (width: T) = w = width
//    if (width > 0) w = width else throw new IllegalArgumentException("width <= 0")

  def height = h
  def height_= (height: T) = h = height
//    if (height > 0) h = height else throw new IllegalArgumentException("height <= 0")

  def length = l
  def length_= (length: T) = l = length
//    if (length > 0) l = length else throw new IllegalArgumentException("length <= 0")

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
//  def perimeter = Box.perimeter(w, h, l)
//  def squareSurface = Box.squareSurface(w, h, l)
//  def volume = Box.volume(w, h, l)

  // Methods
  def open = println("Открыли коробку!")
  def close = println("Закрыли коробку!")

  // Compare by Volume
//  override def compare(that: BoxGeneric): Int = volume - that.volume
}

