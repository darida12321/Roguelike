import scala.collection.mutable
import scala.collection.Set

class RoomMap {
  private var contents = Set.empty[Entity]
  private var rooms = Set.empty[Room]

}

class Room(es: Set[Entity]) {
  private var contents = es
  private var up: Option[Room] = 
}
