
object Main extends App {
  val img = new Image(10, 5)
  img.clear('?')
  img.setChar(1, 2, 'a')
  img.display()
}
