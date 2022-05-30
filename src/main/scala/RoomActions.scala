

object RoomActions {
  def move(e: Entity, dir: Direction): Unit = {
    if(e.room.isDefined && e.room.get.roomAt(dir).isDefined){
      val otherRoom: Room = e.room.get.roomAt(dir).get
      e.room.get.removeEntity(e)
      otherRoom.addEntity(e)
    }
  }
}
