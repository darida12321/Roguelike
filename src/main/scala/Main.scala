
object Main extends App {
  val spr = new SingleColourSprite("+=+\n+=+", Console.YELLOW)


  val img = new Image(10, 5)
  img.setChar(1, 2, 'a')
  img.setChar(1, 3, 'r', Console.RED)
  img.drawSprite(5, 1, spr)
  img.display()
}
