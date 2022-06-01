trait Entity {
  val char: Char 
  val colour: String
  val visible: Boolean
  protected var room: Option[Room] = None
  def getRoom(): Option[Room] = room
  def setRoom(r: Option[Room]): Unit = { 
    if(r.isDefined && !r.get.containsEntity(this)){
      throw new IllegalArgumentException(s"Changing the room variable to a room that doesn't contain this entity is illegal")
    }
    room = r
  }
}

class TestEntity(val char: Char, val colour: String) extends Entity {
  val visible = true
}
class TestEntity2(val char: Char, val colour: String) extends Entity {
  val visible = false 
}

class Robot() extends Entity {
  val char = 'A'
  val colour = Console.GREEN
  val visible = true
}
