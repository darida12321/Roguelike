import org.scalatest.flatspec.AnyFlatSpec

class CubeCalculatorTest extends AnyFlatSpec {
  "CubeCalculator.cube" should "calculate cubes" in {
    assert(CubeCalculator.cube(3) === 27)
  }
}
