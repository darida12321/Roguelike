import scala.io.StdIn.readLine

object Main extends App {
  /*val map = new TileMap(10, 10)
  val player = new Entity()
  map.place(player, 0, 0)
  var loopAgain = true
  print("\u001b[2J\u001b[;H") 
  while (loopAgain) {
    // TODO offload this shit to a parser
    println(map.display())
    var prompt = readLine("> ")
    prompt match {
      case "u" => map.move(player, -1, 0)
      case "d" => map.move(player, 1, 0)
      case "l" => map.move(player, 0, -1)
      case "r" => map.move(player, 0, 1)
      case "q" => {
        loopAgain = false
      }
      case _ => // do nothing
    }
    print("\u001b[2J\u001b[;H") 
  }*/
  val room1 = new Room(1, 1, Set(
    new TestEntity('A', Console.RED),
    new TestEntity('B', Console.RED),
    new TestEntity('C', Console.RED),
    new TestEntity('d', Console.RED),
    new TestEntity('e', Console.RED),
    new TestEntity('f', Console.RED),
    new TestEntity('g', Console.RED),
    new TestEntity('h', Console.RED),
    new TestEntity('i', Console.RED),
    new TestEntity('j', Console.RED),
    new TestEntity('k', Console.RED)
  ))
  val room2 = new Room(11, 1, Set.empty)

  RoomMap.addRoom(room1)
  RoomMap.addRoom(room2)
  RoomMap.display()
}
