import scala.collection.mutable
import scala.collection.Set

class RoomMap(val w: Int, val h: Int) {
  var rooms = Set.empty[Room]
  
  def addRoom(room: Room) = {
    rooms += room
  }
  def getRoom(id: Int) = {
    val rs:Set[Room] = rooms.filter(r => r.id == id)
    if(rs.size == 0){
      None
    } else {
      rs.head
    }
  }

  def display(): Unit = {
    val image = new Image(w, h)
    for(room <- rooms){
      room.displaySelf(image)
    }
    image.display()
  }
}

object Room {
  val WIDTH = 5
  val HEIGHT = 2

  var str = "+-----+\n|     |\n|     |\n+-----+"
  var sprite: Sprite = new SingleColourSprite(str)
}

class Room(x: Int, y: Int, val id: Int, private var es: Set[Entity]) {
  val connections = Array.ofDim[Option[Room]](4)
  for (i <- Right.index to Down.index) {
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

  def addEntity(e: Entity): Unit = { 
    e.room = Some(this)
    es += e
  }
  def removeEntity(e: Entity): Boolean = {
    if(es.contains(e)){
      e.room = None
      es -= e
      true
    } else { false }
  }
  def containsEntity(e: Entity): Boolean = es.contains(e)

  // TODO maybe move this outside?
  def displaySelf(img: Image): Unit = {
    val rx: Int = 1 + x * (Room.WIDTH + 5) 
    val ry: Int = 0 + y * (Room.HEIGHT + 3)
    img.drawSprite(rx, ry, Room.sprite)

    //TODO order the entities in importance
    val displayed = es.filter(e => e.visible).take(10).toList
    for(i <- 0 until displayed.length){
      val e = displayed(i)
      img.setChar(rx+i%5+1, ry+i/5+1, e.char, e.colour)
    }
    img.setChar(rx+Room.WIDTH/2+1, ry, id.toString.charAt(0))
    
    // TODO: Use sprites for corridors
    // TODO: Dispay corridors when other side invisible
    if(connections(Right.index) != None){
      img.setChar(rx+Room.WIDTH+2, ry+1, '-')
      img.setChar(rx+Room.WIDTH+3, ry+1, '-')
      img.setChar(rx+Room.WIDTH+4, ry+1, '-')
      img.setChar(rx+Room.WIDTH+2, ry+2, '-')
      img.setChar(rx+Room.WIDTH+3, ry+2, '-')
      img.setChar(rx+Room.WIDTH+4, ry+2, '-')
    }

    if(connections(Down.index) != None){
      img.setChar(rx+2, ry+Room.HEIGHT+2, '|')
      img.setChar(rx+4, ry+Room.HEIGHT+2, '|')
    }
  }
}


