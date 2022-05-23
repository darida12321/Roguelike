import scala.io.StdIn.readLine

object Main extends App {
  val e1 = new TestEntity('A', Console.RED);
  val e2 = new TestEntity('B', Console.RED);
  val room1 = new Room(1, 0, Set(
    e1,
    new TestEntity('j', Console.RED),
    new TestEntity('k', Console.RED),
    new Robot()
  ))

  room1.removeEntity(e1)
  room1.addEntity(e2)

  val room2 = new Room(11, 0, Set.empty)
  val room3 = new Room(11, 5, Set.empty)
  room2.connect(Left, room1)
  room3.connect(Up, room2)

  RoomMap.addRoom(room1)
  RoomMap.addRoom(room2)
  RoomMap.addRoom(room3)
  RoomMap.display()
}
