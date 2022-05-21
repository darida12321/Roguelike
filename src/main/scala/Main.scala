
object Main extends App {
  val spr = new Sprite("+=+\n+=+")

  val img = new Image(10, 5)
  img.setChar(1, 2, 'a')
  img.setChar(1, 3, 'r', Console.RED)
  img.drawSpr(5, 1, spr)
  img.display()
}
