import scala.io.StdIn.readLine

object Main extends App {
  val e1 = new Robot();
  
  // TODO Add tests
  // TODO Refactor room display
  // TODO Add box sprite
  val roomMap = MapGenerator.getPredefinedMap()
  roomMap.rooms.head.addEntity(e1)

  val room = roomMap.getRoom(2)
  
  roomMap.display()

  // TODO add input handler
  // Thinking of a rotation between Entities - if it's a player entity, we allow input
}
