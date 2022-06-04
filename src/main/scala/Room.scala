import scala.collection.mutable
import scala.collection.Set

// Some default values for the room
object Room {
  val WIDTH = 5
  val HEIGHT = 2

  var str = "+-----+\n|     |\n|     |\n+-----+"
  var sprite: Sprite = new SingleColourSprite(str)
}

// A Room implementation with a set of entities, and connections
class Room(x: Int, y: Int, val id: Int, private var es: Set[Entity]) {
  // Set up its connections
  private val connections = Array.ofDim[Option[Room]](4)
  for (i <- Right.index to Down.index) {
    connections(i) = None
  }
  // Set up the entity list
  for (e <- es) {
    if(e.getRoom() != None){
      throw new IllegalArgumentException(s"Entity $e is already in another room")
    }
    e.setRoom(this)
  }
  
  def this(x: Int, y: Int, id: Int) = {
    this(x, y, id, Set.empty)
  }

  // Connect to other rooms
  def roomAt(d: Direction): Option[Room] = connections(d.index)
  def connect(d: Direction, r: Room): Unit = {
    this.connections(d.index) = Some(r)
    r.connections(Direction.invert(d).index) = Some(this)
  }

  // Add and remove entities
  def addEntity(e: Entity): Unit = { 
    if(es.contains(e)){
      throw new IllegalArgumentException(s"Entity $e was already inside room.")
    }
    if(e.getRoom() != None){
      throw new IllegalArgumentException(s"Entity $e is already in room ${e.getRoom().get}")
    }
    es += e
    e.setRoom(Some(this))
  }
  def removeEntity(e: Entity): Unit = {
    if(!es.contains(e)){
      throw new IllegalArgumentException(s"Entity $e was not inside room $this when removed")
    }
    es -= e
    e.setRoom(None)
  }
  def containsEntity(e: Entity): Boolean = es.contains(e)
  def getEntities(): Set[Entity] = es

  // Display itself on an image
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


