
class SingleColourSprite(str: String, col: String) extends Sprite {
  def this(str: String){
    this(str, Console.RESET)
  }
  val lines = str.split("\n")
  for(line <- lines){
    if(line.length != lines(0).length){
      throw new Exception(s"Sprite string is not a rectangle for \n$str")
    }
  }

  val width = lines(0).length
  val height = lines.length
  def getContent(x: Int, y: Int): Char = {
    if (pointInside(x, y)) {
      lines(y)(x)
    }else{
      throw new IndexOutOfBoundsException(s"Index ($x, $y) is out of bounds for image of size ($width, $height)")
    }
  }
  def getColour(x: Int, y: Int): String = {
    if (pointInside(x, y)) {
      col
    }else{
      throw new IndexOutOfBoundsException(s"Index ($x, $y) is out of bounds for image of size ($width, $height)")
    }
  }

  def pointInside(x: Int, y: Int): Boolean = {
    0 <= x && x < width && 0 <= y && y < height
  }
}
