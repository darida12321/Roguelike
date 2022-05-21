
trait Sprite {
  def width: Int
  def height: Int
  def getContent(x: Int, y: Int): Char
  def getColour(x: Int, y: Int): String 
}
