import scala.io.StdIn.readLine

object Main extends App {
  val e1 = new Robot();
  
  // TODO Add room index
  // TODO Add tests
  val roomMap = MapGenerator.getPredefinedMap()
  roomMap.rooms.head.addEntity(e1)
  
  roomMap.display()
}
