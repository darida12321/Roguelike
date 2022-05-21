
class Sprite(width: Int, height: Int) {
  val content = Array.ofDim[Char](width, height)
  val colour = Array.ofDim[String](width, height)

  def clear(c: Char): Unit = clear(c, Console.RESET)
  def clear(c: Char, col: String): Unit = {
    for(x <- 0 until width){
      for(y <- 0 until height){
        content(x)(y) = c
        colour(x)(y) = col
      }
    }
  }

  def display(): Unit = {
    var data: String = ""

    for(y <- 0 until height){
      for(x <- 0 until width){
        data += colour(x)(y) + content(x)(y)
      }
      data += "\n"
    }
    print(data)
  }
}
