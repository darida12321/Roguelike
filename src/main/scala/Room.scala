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

object Room {
  val WIDTH = 5
  val HEIGHT = 2

  var str = "+--1--+\n|     |\n|     |\n+-----+"
  var sprite: Sprite = new SingleColourSprite(str)
}
class Room(x: Int, y: Int, private var es: Set[Entity]) {
  private val connections = Array.ofDim[Option[Room]](4)
  for (i <- 0 until 4) {
    connections(i) = None
  }

  def roomAt(d: Direction): Option[Room] = connections(d.index)

  def connect(d: Direction, r: Room): Unit = {
    this.connections(d.index) = Some(r)
    r.connections(Direction.invert(d).index) = Some(this)
  }

  def displaySelf(img: Image): Unit = {
    img.drawSprite(x, y, Room.sprite)

    //TODO order the entities in importance
    val displayed = es.filter(e => e.visible).take(10).toList
    for(i <- 0 until displayed.length){
      val e = displayed(i)
      img.setChar(x+i%5+1, y+i/5+1, e.char, e.colour)
    }

    //TODO display corridors
  }
}

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

