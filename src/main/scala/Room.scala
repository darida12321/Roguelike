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

class Room(x: Int, y: Int, private var es: Set[Entity]) {
  private var str = "+--1--+\n|     |\n|     |\n+-----+"
  private var sprite: Sprite = new SingleColourSprite(str)
  
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
    img.drawSprite(x, y, sprite)
    var xOff = 1
    var yOff = 1
    //TODO order the entities in importance
    for(e <- es.filter(e => e.visible).take(10)){
      img.setChar(x+xOff, y+yOff, e.char, e.colour)
      xOff += 1;
      if(xOff > 5){
        xOff = 1;
        yOff += 1
      }
    }
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

