import scala.io.StdIn.readLine

object Main extends App {
  val map = new TileMap(10, 10)
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
  }
}
