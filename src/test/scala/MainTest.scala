import org.scalatest.funsuite.AnyFunSuite
import Main._

class MainTest extends AnyFunSuite {
  test("Stuff is correct") {
    assert(Main.add(1, 2) == 3)
  }
}
