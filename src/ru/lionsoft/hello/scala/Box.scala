package ru.lionsoft.hello.scala

/**
 * Класс описывающий коробку
 * (Конструктор коробки заданного размера)
 * @param w ширина коробки
 * @param h высота коробки
 * @param l длина коробки
 */
class Box(private var w: Int = 1,
          private var h: Int = 1,
          private var l: Int = 1) extends Serializable with Ordered[Box] {
  // Default Constructor Body
  if (w <= 0) throw new IllegalArgumentException("width <= 0")
  if (h <= 0) throw new IllegalArgumentException("height <= 0")
  if (l <= 0) throw new IllegalArgumentException("length <= 0")

  /**
   * Конструктор кубической коробки
   * @param size
   */
  def this(size: Int) = this(size, size, size)

  /**
   * Конструктор коробки стандартного типоразмера
   * @param typeSize стандартный типоразмер коробки
   */
  def this(typeSize: Char) = {
    this() // !!!! Mandatory !!!!
    typeSize match {
      case 'S' => w = 1; h = 2; l = 3;
      case 'M' => w = 4; h = 5; l = 6;
      case 'L' => w = 7; h = 8; l = 9;
      case 'X' => w = 10; h = 11; l = 12;
    }
  }

  // Getters & Setters
  def width = w
  def width_= (width: Int) =
    if (width > 0) w = width else throw new IllegalArgumentException("width <= 0")

  def height = h
  def height_= (height: Int) =
    if (height > 0) h = height else throw new IllegalArgumentException("height <= 0")

  def length = l
  def length_= (length: Int) =
    if (length > 0) l = length else throw new IllegalArgumentException("length <= 0")

  def apply(): (Int, Int, Int) = (w, h, l)
  def update(value: (Int, Int, Int)): Unit = {
    width = value._1
    height = value._2
    length = value._3
  }

  // Cast to String
  override def toString: String = s"Box{width=$w, height=$h, length=$l}"

  // Equals & HashCode
  def canEqual(other: Any): Boolean = other.isInstanceOf[Box]

  override def equals(other: Any): Boolean = other match {
    case that: Box =>
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

//  import Box._
  /**
   * Получить периметр коробки
   * @return
   */
  def perimeter = Box.perimeter(w, h, l)
  def squareSurface = Box.squareSurface(w, h, l)
  def volume = Box.volume(w, h, l)

  // Methods
  def open = println("Открыли коробку!")
  def close = println("Закрыли коробку!")

  // Compare by Volume
  override def compare(that: Box): Int = volume.compare(that.volume)
}

// Java static
object Box {
  // Constants
  val TYPE_SIZE_SMALL = 'S'
  val TYPE_SIZE_MEDIUM = 'M'
  val TYPE_SIZE_LARGE = 'L'
  val TYPE_SIZE_EXTRA_LARGE = 'X'

  // Fabric Method

  /**
   * Альтернативный контсруктор (фабричный метод)
   * @param width  ширина коробки
   * @param height длина коробки
   * @param length высота коробки
   * @return созданная коробк
   */
  def apply(width: Int, height: Int, length: Int) = new Box(width, height, length)

  /**
   * Альтернативный контсруктор коробки стандартного типоразмера (фабричный метод)
   * @param typeSize стандартный типоразмер коробки
   * @return созданная коробк
   */
  def apply(typeSize: Char): Box = typeSize match {
    case 'S' => new Box(1, 2, 3)
    case 'M' => new Box(4, 5, 6)
    case 'L' => new Box(7, 8, 9)
    case 'X' => new Box(10, 11, 12)
    case _ => new Box
  }

  /**
   * Развертывание коробки на параметры: (ширина, высота, длина)
   * @param box ссылка на коробку
   * @return кортеж параметров (ширина, высота, длина)
   */
  def unapply(box: Box): Option[(Int, Int, Int)] = Option(box.width, box.height, box.length)

  // static function
  /**
   * Вычислить периметр коробки
   * @param width ширина коробки
   * @param height высота коробки
   * @param length длина коробки
   * @return периметр коробки (длина ребер)
   */
  def perimeter(width: Double, height: Double, length: Double) = (width + height + length) * 4
  def squareSurface(width: Double, height: Double, length: Double) = (width * height + width * length + height * length) * 2
  def volume(width: Double, height: Double, length: Double) = width * height * length
}