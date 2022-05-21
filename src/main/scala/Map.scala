class Map(x: Int, y: Int) {
  def getWidth(): Int = x
  def getHeight(): Int = y

  val grid = Array.ofDim[Tile](x, y)
  for (i <- 0 until x) {
    for (j <- 0 until y) {
      grid(i)(j) = Empty
    }
  }
  

  def display(): String = grid.map(_.map(
      (t : Tile) => t match {
        case Empty => "O"
        case Full(s) => s
      }
    ).toString() + "\n").toString()
}

sealed trait Tile
object Empty extends Tile
case class Full(s: String) extends Tile
