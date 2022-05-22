import scala.collection.mutable
import scala.collection.Set

object RoomMap {
  private var contents = Set.empty[Entity]
  private var rooms = Set.empty[Room]
  
  def addRoom(room: Room) = {
    rooms += room
  }
  def display(): Unit = {
    val image = new Image(30, 10)
    for(room <- rooms){
      room.displaySelf(image)
    }
    image.display()
  }
}

class Room(x: Int, y: Int, es: Set[Entity]) {
  private var str = ""
  str += 0x256D.toChar + 0x2500.toChar.toString * 5 + 0x256E.toChar + '\n'
  str += (0x2502.toChar + " " * 5 + 0x2502.toChar + '\n') * 2
  str += 0x2570.toChar + 0x2500.toChar.toString * 5 + 0x256F.toChar

  private var sprite: Sprite = new SingleColourSprite(str)
  private var contents = es
  
  private val connections = Array.ofDim[Option[Room]](4)
  for (i <- 0 until 4) {
    connections(i) = None
  }

  def up(): Option[Room] = connections(0)
  def down(): Option[Room] = connections(1)
  def left(): Option[Room] = connections(2)
  def right(): Option[Room] = connections(3)

  def displaySelf(img: Image): Unit = {
    img.drawSprite(x, y, sprite)
  }
}
