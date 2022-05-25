import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ImageTest extends AnyFlatSpec {
  behavior of "Empty image"
  it should "have the correct size" in {
    new Image(1, 1).getContent() shouldBe " \n"
    new Image(3, 1).getContent() shouldBe "   \n"
    new Image(1, 3).getContent() shouldBe " \n \n \n"
    new Image(2, 4).getContent() shouldBe "  \n  \n  \n  \n"
    new Image(4, 2).getContent() shouldBe "    \n    \n"
  }
  it should "throw an error when given a wrong size" in {
    an [IllegalArgumentException] should be thrownBy new Image(0, 0)
    an [IllegalArgumentException] should be thrownBy new Image(-1, 0)
    an [IllegalArgumentException] should be thrownBy new Image(0, -2)
    an [IllegalArgumentException] should be thrownBy new Image(-2, -1)
    an [IllegalArgumentException] should be thrownBy new Image(0, 2)
    an [IllegalArgumentException] should be thrownBy new Image(1, 0)
    an [IllegalArgumentException] should be thrownBy new Image(-1, 5)
  }

  behavior of "Clear"
  it should "fill the image with the correct character" in {
    val img = new Image(2, 2)
    img.clear('a')
    img.getContent() shouldBe "aa\naa\n"
    img.clear('_')
    img.getContent() shouldBe "__\n__\n"
    img.clear('\t')
    img.getContent() shouldBe "\t\t\n\t\t\n"
  }

  behavior of "Setting a character"
  it should "change the correct character" in {
    val img = new Image(2, 2)
    img.setChar(0, 0, 'a')
    img.getContent() shouldBe "a \n  \n"
    img.setChar(1, 0, 'b')
    img.getContent() shouldBe "ab\n  \n"
    img.setChar(0, 1, 'c')
    img.getContent() shouldBe "ab\nc \n"
    img.setChar(1, 1, 'd')
    img.getContent() shouldBe "ab\ncd\n"
  }
}
