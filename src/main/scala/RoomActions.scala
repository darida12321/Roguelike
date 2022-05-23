

object RoomActions {
  def move(e: Entity, dir: Direction): Unit = {
    if(e.room.isDefined && e.room.get.connections(dir.index).isDefined){
      val otherRoom: Room = e.room.get.connections(dir.index).get
      e.room.get.removeEntity(e)
      otherRoom.addEntity(e)
    }
  }
}
