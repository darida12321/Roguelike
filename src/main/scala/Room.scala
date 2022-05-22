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

  def up(): Option[Room] = connections(0)
  def down(): Option[Room] = connections(1)
  def left(): Option[Room] = connections(2)
  def right(): Option[Room] = connections(3)

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
