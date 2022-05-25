
class Image(width: Int, height: Int) {
  if(width <= 0 || height <= 0){
    throw new IllegalArgumentException(s"Width and height must be greater than 0. ($width, $height)")
  }

  val content = Array.ofDim[Char](width, height)
  val colour = Array.ofDim[String](width, height)
  clear(' ')

  def clear(c: Char): Unit = clear(c, Console.RESET)
  def clear(c: Char, col: String): Unit = {
    for(x <- 0 until width){
      for(y <- 0 until height){
        content(x)(y) = c
        colour(x)(y) = col
      }
    }
  }

  private def pointInside(x: Int, y: Int): Boolean = {
    0 <= x && x < width && 0 <= y && y < height
  }
  def setChar(x: Int, y: Int, c: Char): Boolean = setChar(x, y, c, Console.RESET)
  def setChar(x: Int, y: Int, c: Char, col: String): Boolean = {
    if (pointInside(x, y)) {
      content(x)(y) = c
      colour(x)(y) = col
      true
    }else{
      false
    }
  }
  def getChar(x: Int, y: Int): Char = {
    if (pointInside(x, y)) {
      content(x)(y)
    }else{
      throw new IndexOutOfBoundsException(s"Index ($x, $y) is out of bounds for image of size ($width, $height)")
    }
  }

  def drawSprite(x: Int, y: Int, spr: Sprite): Boolean = {
    val w = spr.width
    val h = spr.height
    if (pointInside(x, y) && pointInside(x+w, y+h)) {
      for(xx <- 0 until w){
        for(yy <- 0 until h){
          content(x+xx)(y+yy) = spr.getContent(xx, yy)
          colour(x+xx)(y+yy) = spr.getColour(xx, yy)
        }
      }
      true
    }else{
      false
    }
  }

  def getContent(): String = {
    var data: String = ""
    for(y <- 0 until height){
      for(x <- 0 until width){
        data += content(x)(y)
      }
      data += "\n"
    }
    data
  }
  def display(): Unit = {
    val h = 0x2501.toChar.toString
    val v = 0x2503.toChar.toString
    val tl = 0x250f.toChar.toString
    val tr = 0x2513.toChar.toString
    val bl = 0x2517.toChar.toString
    val br = 0x251b.toChar.toString

    var data: String = ""
    data += tl + h*width + tr + "\n"
    for(y <- 0 until height){
      data += Console.RESET
      data += v 
      for(x <- 0 until width){
        data += colour(x)(y) + content(x)(y)
      }
      data += Console.RESET
      data += v + "\n" 
    }
    data += Console.RESET
    data += bl + h*width + br + "\n"
    print(data)
  }
}
