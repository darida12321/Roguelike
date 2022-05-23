import scala.io.StdIn.readLine

object Main extends App {
  val room1 = new Room(1, 0, Set(
    new TestEntity('A', Console.RED),
    new TestEntity('j', Console.RED),
    new TestEntity('k', Console.RED),
    new Robot()
  ))
  val room2 = new Room(11, 0, Set.empty)
  val room3 = new Room(11, 5, Set.empty)
  room2.connect(Left, room1)
  room3.connect(Up, room2)

  RoomMap.addRoom(room1)
  RoomMap.addRoom(room2)
  RoomMap.addRoom(room3)
  RoomMap.display()
}
