import scala.collection.mutable
import scala.collection.Set

object RoomMap {
  private var contents = Set.empty[Entity]
  var rooms = Set.empty[Room]
}

class Room(es: Set[Entity]) {
  private var contents = es
  private val connections = Array.ofDim[Option[Room]](4)
  for (i <- 0 until 4) {
    connections(i) = None
  }

  def roomAt(d: Direction): Option[Room] = connections(d.index)

  def connect(d: Direction, r: Room): Unit = {
    this.connections(d.index) = Some(r)
    r.connections(Directions.invert(d).index) = Some(this)
  }
}

sealed trait Direction {
  val index: Int
}
object Direction {
  def fromInt(i: Int) = i match {
    case 0 => Right
    case 1 => Up
    case 2 => Left
    case 3 => Down
  }

  def Invert(d: Direction): Direction = fromInt((d.index + 2) % 4)
  def RotateRight(d: Direction): Direction = fromInt((d.index + 3) % 4)
  def RotateLeft(d: Direction): Direction = fromInt((d.index + 1) % 4)
}

object Right {
  override val index = 0
}
object Up {
  override val index = 1
}
object Left {
  override val index = 2
}
object Down {
  override val index = 3
}

