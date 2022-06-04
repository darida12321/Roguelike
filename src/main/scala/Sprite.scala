
// A Sprite to display on images
trait Sprite {
  val width: Int
  val height: Int
  def getContent(x: Int, y: Int): Char
  def getColour(x: Int, y: Int): String 
}

// A sprite from a string with a single colour
class SingleColourSprite(str: String, col: String) extends Sprite {
  // Constructor to get the content of the given string
  val lines = str.split("\n")
  if(lines.length == 1 && lines(0).length == 0){
    throw new IllegalArgumentException(s"A sprite cannot be created from an empty string.")
  }
  for(line <- lines){
    if(line.length != lines(0).length){
      throw new IllegalArgumentException(s"Sprite string is not a rectangle for \n$str")
    }
  }
  
  // Constructor with default colour
  def this(str: String){
    this(str, Console.RESET)
  }

  // Get width and height
  val width = lines(0).length
  val height = lines.length

  // Get content and colour
  def getContent(x: Int, y: Int): Char = {
    if (!pointInside(x, y)) {
      throw new IndexOutOfBoundsException(s"Index ($x, $y) is out of bounds for image of size ($width, $height)")
    }
    lines(y)(x)
  }
  def getColour(x: Int, y: Int): String = {
    if (!pointInside(x, y)) {
      throw new IndexOutOfBoundsException(s"Index ($x, $y) is out of bounds for image of size ($width, $height)")
    }
    col
  }
  
  // Check if a given point is inside the sprite
  def pointInside(x: Int, y: Int): Boolean = {
    0 <= x && x < width && 0 <= y && y < height
  }
}

// A sprite for drawing a box outline
// The chars is: tl, tr, bl, br, horizontal, vertical, inside
class BoxSprite(val width: Int, val height: Int, chars: String, col: String) extends Sprite {
  // Constructor to chack that the arguments are valid
  if(chars.length != 7){
    throw new IllegalArgumentException(s"The input $chars does not specify a box texture. You must give: (tl, tr, bl, br, h, v, inside) characters.")
  }
  if(width <= 0 || height <= 0){
    throw new IllegalArgumentException(s"The size ($width, $height) is not a valid size for a sprite.")
  }
  
  // Constructor with default colour
  def this(w: Int, h: Int, chars: String){
    this(w, h, chars, Console.RESET)
  }

  // Get the content at a given point based on where the point lies
  def getContent(x: Int, y: Int): Char = {
    if (!pointInside(x, y)) {
      throw new IndexOutOfBoundsException(s"Index ($x, $y) is out of bounds for image of size ($width, $height)")
    }
    var c = chars.charAt(6) // Inside
    if (y == 0 || y == height-1) { c = chars.charAt(4) } // H
    if (x == 0 || x == width-1 ) { c = chars.charAt(5) } // V
    if (x == 0       && y == 0       ) { c = chars.charAt(0) } // TL
    if (x == width-1 && y == 0       ) { c = chars.charAt(1) } // TR
    if (x == 0       && y == height-1) { c = chars.charAt(2) } // BL
    if (x == width-1 && y == height-1) { c = chars.charAt(3) } // BR
    c
  }
  
  // Return the default colour
  def getColour(x: Int, y: Int): String = {
    if (!pointInside(x, y)) {
      throw new IndexOutOfBoundsException(s"Index ($x, $y) is out of bounds for image of size ($width, $height)")
    }
    col
  }

  // Get if a point is inside the sprite
  def pointInside(x: Int, y: Int): Boolean = {
    0 <= x && x < width && 0 <= y && y < height
  }
}
