

object RoomActions {
  def move(e: Entity, dir: Direction): Unit = {
    if(e.getRoom().isDefined && e.getRoom().get.roomAt(dir).isDefined){
      val otherRoom: Room = e.getRoom().get.roomAt(dir).get
      e.getRoom().get.removeEntity(e)
      otherRoom.addEntity(e)
    }
  }
}
