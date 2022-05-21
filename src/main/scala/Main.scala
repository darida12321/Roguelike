import scala.io.StdIn.readLine

object Main extends App {
  val map = new TileMap(10, 10)
  var pos_x = 0
  var pos_y = 0
  val player = new Entity()
  map.place(player, pos_x, pos_y)
  var loopAgain = true
  while (loopAgain) {
    // TODO offload this shit to a parser
    println(map.display())
    var prompt = readLine("> ")
    prompt match {
      case "u" => {
        map.move(player, pos_x - 1, pos_y)
        pos_x = pos_x - 1
      }
      case "d" => {
        map.move(player, pos_x + 1, pos_y)
        pos_x = pos_x + 1
      }
      case "l" => {
        map.move(player, pos_x, pos_y - 1)
        pos_y = pos_y - 1
      }
      case "r" => { 
        map.move(player, pos_x, pos_y + 1)
        pos_y = pos_y + 1
      }
      case "q" => {
        loopAgain = false
      }
      case _ => {
        // do nothing
      }
    }
  }
}
