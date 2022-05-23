import scala.io.StdIn.readLine

object Main extends App {
  val e1 = new Robot();

  val roomMap = MapGenerator.getPredefinedMap()
  roomMap.rooms.head.addEntity(e1)
  
  roomMap.display()
}
