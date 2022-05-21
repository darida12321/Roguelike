
class Image (width: Int, height: Int) {
  //TODO colour
  //TODO sprites
  val content = Array.ofDim[Char](width, height)
  clear(' ')

  def clear(c: Char): Unit = {
    for(x <- 0 until width){
      for(y <- 0 until height){
        content(x)(y) = c
      }
    }
  }

  def pointInside(x: Int, y: Int): Boolean = {
    0 <= x && x < width && 0 <= y && y < height
  }
  def setChar(x: Int, y: Int, c: Char): Boolean = {
    if (pointInside(x, y)) {
      content(x)(y) = c
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


  def display(): Unit = {
    var data: String = ""
    val h = 0x2501.toChar.toString
    val v = 0x2503.toChar.toString
    val tl = 0x250f.toChar.toString
    val tr = 0x2513.toChar.toString
    val bl = 0x2517.toChar.toString
    val br = 0x251b.toChar.toString

    data += tl + h*width + tr + "\n"
    for(y <- 0 until height){
      data += v 
      for(x <- 0 until width){
        data += content(x)(y)
      }
      data += v + "\n" 
    }
    data += bl + h*width + br + "\n"
    print(data)
  }
}
