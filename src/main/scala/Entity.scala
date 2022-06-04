
// An entity trait having a character to display, and a room where it belongs
trait Entity {
  val char: Char 
  val colour: String
  val visible: Boolean

  // A room variable updated to its current room
  private var room: Option[Room] = None
  def getRoom(): Option[Room] = room
  def setRoom(r: Option[Room]): Unit = { 
    if(r.isDefined && !r.get.containsEntity(this)){
      throw new IllegalArgumentException(s"Changing the room variable to a room that doesn't contain this entity is illegal")
    }
    room = r
  }
  def setRoom(r: Room): Unit = { 
    setRoom(Some(r))
  }

  // Move rooms while updating the entities in those rooms
  def moveRoom(r: Option[Room]): Unit = {
    if(room.isDefined){
      room.get.removeEntity(this)
    }
    if(r.isDefined){
      r.get.addEntity(this)
    }
    room = r
  }
  def moveRoom(r: Room): Unit = {
    moveRoom(Some(r))
  }
}

// A test robot entity
class Robot() extends Entity {
  val char = 'A'
  val colour = Console.GREEN
  val visible = true
}
