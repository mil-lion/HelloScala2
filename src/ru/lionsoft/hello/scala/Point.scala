package ru.lionsoft.hello.scala

/**
 * Класс описывающий точку в 2D координатах
 * @param x абцисса
 * @param y ордината
 */
class Point(private var _x: Int = 0, private var _y: Int = 0) {

  private val bound = 100

  // Getters & Setters

  // Getter
  def x = _x
  // Setter
  def x_= (newValue: Int) = {
    if (newValue >= -bound && newValue <= bound) _x = newValue else printWarning
  }

  def y = _y
  def y_= (newValue: Int) = {
    if (newValue >= -bound && newValue <= bound) _y = newValue else printWarning
  }

  private def printWarning = println("WARNING: Out of bounds")

  /**
   * Переместить точку
   * @param dx смещение по абцисе
   * @param dy смещение по ординате
   */
  def move(dx: Int, dy: Int): Unit = {
    x += dx
    y += dy
  }

  // Cast to String
  override def toString: String = s"Point($x, $y)"

}
