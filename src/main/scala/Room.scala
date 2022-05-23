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
  val connections = Array.ofDim[Option[Room]](4)
  for (i <- 0 until 4) {
    connections(i) = None
  }
  for (e <- es) {
    e.room = Some(this)
  }

  def roomAt(d: Direction): Option[Room] = connections(d.index)
  def connect(d: Direction, r: Room): Unit = {
    this.connections(d.index) = Some(r)
    r.connections(Direction.invert(d).index) = Some(this)
  }

  def addEntity(e: Entity): Unit = { es += e }
  def removeEntity(e: Entity): Boolean = {
    if(es.contains(e)){
      es -= e
      true
    } else { false }
  }
  def containsEntity(e: Entity): Boolean = es.contains(e)

  def displaySelf(img: Image): Unit = {
    img.drawSprite(x, y, Room.sprite)

    //TODO order the entities in importance
    val displayed = es.filter(e => e.visible).take(10).toList
    for(i <- 0 until displayed.length){
      val e = displayed(i)
      img.setChar(x+i%5+1, y+i/5+1, e.char, e.colour)
    }

    //TODO display corridors
    if(connections(Right.index) != None){
      img.setChar(x+Room.WIDTH+2, y+1, '-')
      img.setChar(x+Room.WIDTH+3, y+1, '-')
      img.setChar(x+Room.WIDTH+4, y+1, '-')
      img.setChar(x+Room.WIDTH+2, y+2, '-')
      img.setChar(x+Room.WIDTH+3, y+2, '-')
      img.setChar(x+Room.WIDTH+4, y+2, '-')
    }

    if(connections(Down.index) != None){
      img.setChar(x+2, y+Room.HEIGHT+2, '|')
      img.setChar(x+4, y+Room.HEIGHT+2, '|')
    }
  }
}


