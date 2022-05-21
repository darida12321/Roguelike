import scala.io.StdIn.readLine

object Main extends App {
  val map = new Map(10, 10)
  var pos_x = 0
  var pos_y = 0
  map.grid(pos_x)(pos_y) = Full("C")
  var loopAgain = true
  while (loopAgain) {
    println(map.display())
    var prompt = readLine("> ")
    prompt match {
      case "u" => updatePos(-1, 0)
      case "d" => updatePos(1, 0)
      case "l" => updatePos(0, -1)
      case "r" => updatePos(0, 1)
      case _ => {
        loopAgain = false
      }
    }
  }

  def updatePos(x_off: Int, y_off: Int): Unit = {
    map.grid(pos_x + x_off)(pos_y + y_off) = map.grid(pos_x)(pos_y)
    map.grid(pos_x)(pos_y) = Empty
    pos_x = pos_x + x_off
    pos_y = pos_y + y_off
  }
}
