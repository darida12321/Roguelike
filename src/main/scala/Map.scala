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

  // places a new entity at point (i, j) on the grid
  def place(e: Entity, i: Int, j: Int): Unit = dict.get(e) match {
    case Some(_) =>
    case _ => {
      dict += (e -> (i, j))
      grid(i)(j) = Room(grid(i)(j).es + e)
    }
  }
  
  // directly moves entity e to tile (i, j) on the grid
  // TODO handle preventing movement into tiles that do not exist
  def moveTo(e: Entity, i: Int, j: Int): Unit = dict.get(e) match {
    case Some((x: Int, y: Int)) => {
      grid(x)(y) = Room(grid(x)(y).es - e)
      dict(e) = (i, j)
      grid(i)(j) = Room(grid(i)(j).es + e)
    }
    case _ => // do nothing
  }

  // moves entity an offset of (i, j) from their current location
  // TODO handle preventing movement into tiles that do not exist
  def move(e: Entity, i: Int, j: Int): Unit = dict.get(e) match {
    case Some((x, y)) => moveTo(e, x + i, y + j)
    case _ => // do nothing
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
