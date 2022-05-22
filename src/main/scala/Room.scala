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
  private var sprite: Sprite = new SingleColourSprite("aaa\naaa\naaa")
  private var contents = es
  
  def displaySelf(img: Image): Unit = {
    img.drawSprite(x, y, sprite)
  }
}
