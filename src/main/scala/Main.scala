
object Main extends App {
  val img = new Image(10, 5)
  img.clear('?')
  img.setChar(1, 2, 'a')
  img.setChar(1, 3, 'r', Console.RED)
  img.display()
}
