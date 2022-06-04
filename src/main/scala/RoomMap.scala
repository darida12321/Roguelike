
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

