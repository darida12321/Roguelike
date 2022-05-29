
trait Sprite {
  val width: Int
  val height: Int
  def getContent(x: Int, y: Int): Char
  def getColour(x: Int, y: Int): String 
}

class SingleColourSprite(str: String, col: String) extends Sprite {
  def this(str: String){
    this(str, Console.RESET)
  }
  val lines = str.split("\n")
  if(lines.length == 1 && lines(0).length == 0){
    throw new IllegalArgumentException(s"A sprite cannot be created from an empty string.")
  }
  for(line <- lines){
    if(line.length != lines(0).length){
      throw new IllegalArgumentException(s"Sprite string is not a rectangle for \n$str")
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

class BoxSprite(val width: Int, val height: Int, chars: String, col: String) extends Sprite {
  if(chars.length != 7){
    throw new Exception(s"The input $chars does not specify a box texture. You must give: (tl, tr, bl, br, h, v, inside) characters.")
  }

  def this(w: Int, h: Int, chars: String){
    this(w, h, chars, Console.RESET)
  }


  def getContent(x: Int, y: Int): Char = {
    ???
  }
  def getColour(x: Int, y: Int): String = col
}
