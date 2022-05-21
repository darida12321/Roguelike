import scala.collection.mutable
import scala.collection.Set

class TileMap(x: Int, y: Int) {
  def getWidth(): Int = x
  def getHeight(): Int = y
  private val dict = mutable.Map.empty[Entity, (Int, Int)]

  val grid = Array.ofDim[Tile](x, y)
  for (i <- 0 until x) {
    for (j <- 0 until y) {
      grid(i)(j) = Room(Set.empty[Entity])
    }
  }

  def place(e: Entity, i: Int, j: Int): Unit = dict.get(e) match {
    case Some(_) =>
    case _ => {
      dict += (e -> (i, j))
      grid(i)(j) = Room(grid(i)(j).es + e)
    }
  }

  def move(e: Entity, i: Int, j: Int): Unit = dict.get(e) match {
    case Some((x: Int, y: Int)) => {
      grid(x)(y) = Room(grid(x)(y).es - e)
      dict(e) = (i, j)
      grid(i)(j) = Room(grid(i)(j).es + e)
    }
    case _ =>
  }

  // TODO outsource this functionality to the generation of an image.
  def display(): String = grid.map(_.map(
      (t : Tile) => t match {
        case Room(s) => if (s.isEmpty) {
          "O"
        } else {
          0x0D9E.toChar
        }
        case _ => " " 
      }
    ).mkString(" ")).mkString("\n")
}

sealed trait Tile {
  val es: Set[Entity]
}
object Empty extends Tile {
  override val es = Set.empty[Entity]
}
case class Room(s: Set[Entity]) extends Tile {
  override val es = Set.empty[Entity]
}
