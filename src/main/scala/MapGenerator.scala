


object MapGenerator {
  def getPredefinedMap(): RoomMap = {

    val room1 = new Room(1, 0, Set.empty)
    val room2 = new Room(11, 0, Set.empty)
    val room3 = new Room(11, 5, Set.empty)
    room2.connect(Left, room1)
    room3.connect(Up, room2)

    val roomMap = new RoomMap(30, 10)
    roomMap.addRoom(room1)
    roomMap.addRoom(room2)
    roomMap.addRoom(room3)

    return roomMap
  }
}
