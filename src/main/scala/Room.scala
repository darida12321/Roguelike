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

  def up(): Option[Room] = connections(0)
  def down(): Option[Room] = connections(1)
  def left(): Option[Room] = connections(2)
  def right(): Option[Room] = connections(3)
}
