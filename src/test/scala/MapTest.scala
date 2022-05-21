import org.scalatest.flatspec.AnyFlatSpec

class MapTest extends AnyFlatSpec {
  behavior of "Initialization"
  it should "initialize to being empty" in {
    val map = new Map(10, 10)
    map.display()
    for (i <- 0 until 10) {
      for (j <- 0 until 10) {
        assert(map.grid(i)(j) == Empty)
      }
    }
  }
}
