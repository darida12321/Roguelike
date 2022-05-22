sealed trait Direction {
  val index: Int
}
object Direction {
  def fromInt(i: Int): Direction = i match {
    case 0 => Right
    case 1 => Up
    case 2 => Left
    case 3 => Down
  }

  def invert(d: Direction): Direction = fromInt((d.index + 2) % 4)
  def rotateRight(d: Direction): Direction = fromInt((d.index + 3) % 4)
  def rotateLeft(d: Direction): Direction = fromInt((d.index + 1) % 4)
}

object Right extends Direction {
  override val index = 0
}
object Up extends Direction {
  override val index = 1
}
object Left extends Direction {
  override val index = 2
}
object Down extends Direction {
  override val index = 3
}
