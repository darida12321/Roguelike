import scala.io.StdIn.readLine

object Main extends App {
  val map = new TileMap(10, 10)
  val player = new Entity()
  map.place(player, pos_x, pos_y)
  var loopAgain = true
  while (loopAgain) {
    // TODO offload this shit to a parser
    println(map.display())
    var prompt = readLine("> ")
    prompt match {
      case "u" => map.move(player, -1, 0)
      case "d" => map.move(player, 1, 0)
      case "l" => map.move(player, pos_x, pos_y - 1)
      case "r" => map.move(player, pos_x, pos_y + 1)
      case "q" => {
        loopAgain = false
      }
      case _ => // do nothing
    }
  }
}
