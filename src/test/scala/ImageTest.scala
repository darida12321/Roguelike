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
  it should "throw an exception when writing outside the bounds" in {
    val img = new Image(2, 3)
    an [IndexOutOfBoundsException] should be thrownBy img.setChar(-1, 0, 'a')
    an [IndexOutOfBoundsException] should be thrownBy img.setChar(0, -1, 'a')
    an [IndexOutOfBoundsException] should be thrownBy img.setChar(2, 0, 'a')
    an [IndexOutOfBoundsException] should be thrownBy img.setChar(0, 3, 'a')
    an [IndexOutOfBoundsException] should be thrownBy img.setChar(2, 3, 'a')
    an [IndexOutOfBoundsException] should be thrownBy img.setChar(-1, 3, 'a')
    an [IndexOutOfBoundsException] should be thrownBy img.setChar(3, -1, 'a')
  }

  behavior of "Getting a character"
  it should "return the correct character" in {
    val img = new Image(2, 2)
    img.setChar(0, 0, 'a')
    img.setChar(1, 0, 'b')
    img.setChar(0, 1, 'c')
    img.setChar(1, 1, 'd')
    img.getChar(0, 0) shouldBe 'a'
    img.getChar(1, 0) shouldBe 'b'
    img.getChar(0, 1) shouldBe 'c'
    img.getChar(1, 1) shouldBe 'd'
  }
  it should "throw an exception when writing outside the bounds" in {
    val img = new Image(2, 3)
    an [IndexOutOfBoundsException] should be thrownBy img.getChar(-1, 0)
    an [IndexOutOfBoundsException] should be thrownBy img.getChar(0, -1)
    an [IndexOutOfBoundsException] should be thrownBy img.getChar(2, 0)
    an [IndexOutOfBoundsException] should be thrownBy img.getChar(0, 3)
    an [IndexOutOfBoundsException] should be thrownBy img.getChar(2, 3)
    an [IndexOutOfBoundsException] should be thrownBy img.getChar(-1, 3)
    an [IndexOutOfBoundsException] should be thrownBy img.getChar(3, -1)
  }

  class TestSprite() extends Sprite {
    val width = 2
    val height = 2
    def getContent(x: Int, y: Int): Char = {
      var c = ' '
      if (x == 0 && y == 0){ c = 'a' }
      else if (x == 1 && y == 0){ c = 'b' }
      else if (x == 0 && y == 1){ c = 'c' }
      else if (x == 1 && y == 1){ c = 'd' }
      c
    }
    def getColour(x: Int, y: Int): String = Console.RESET
  }
  behavior of "Sprites"
  it should "display the sprite in the correct position" in {
    val img = new Image(5, 5)
    val spr = new TestSprite()
    img.drawSprite(1, 2, spr)
    img.getContent() shouldBe "     \n     \n ab  \n cd  \n     \n"
  }

  it should "throw an exception when out of bounds" in {
    val img = new Image(5, 5)
    val spr = new TestSprite()
    an [IndexOutOfBoundsException] should be thrownBy img.drawSprite(-1, -1, spr)
    an [IndexOutOfBoundsException] should be thrownBy img.drawSprite(1, -1, spr)
    an [IndexOutOfBoundsException] should be thrownBy img.drawSprite(-1, 1, spr)
    an [IndexOutOfBoundsException] should be thrownBy img.drawSprite(5, 5, spr)
    an [IndexOutOfBoundsException] should be thrownBy img.drawSprite(4, 5, spr)
    an [IndexOutOfBoundsException] should be thrownBy img.drawSprite(5, 4, spr)
    an [IndexOutOfBoundsException] should be thrownBy img.drawSprite(5, 5, spr)
    an [IndexOutOfBoundsException] should be thrownBy img.drawSprite(4, 4, spr)
    an [IndexOutOfBoundsException] should be thrownBy img.drawSprite(3, 4, spr)
    an [IndexOutOfBoundsException] should be thrownBy img.drawSprite(4, 3, spr)
  }
}
