
class Image (width: Int, height: Int) {
  val content = Array.ofDim[Char](width, height)
  for(x <- 0 until width){
    for(y <- 0 until height){
      content(x)(y) = '.'
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
