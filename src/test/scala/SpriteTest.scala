import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class SpriteTest extends AnyFlatSpec {
  behavior of "SingleColourSprite"
  it should "correctly parse the size" in {
    new SingleColourSprite("a").width shouldBe 1
    new SingleColourSprite("a").height shouldBe 1
    new SingleColourSprite("aa").width shouldBe 2
    new SingleColourSprite("aa").height shouldBe 1
    new SingleColourSprite("a\na").width shouldBe 1
    new SingleColourSprite("a\na").height shouldBe 2
    new SingleColourSprite("aa\naa").width shouldBe 2
    new SingleColourSprite("aa\naa").height shouldBe 2
  }
  it should "fail on incorrect input" in {
    an [IllegalArgumentException] should be thrownBy new SingleColourSprite("")
    an [IllegalArgumentException] should be thrownBy new SingleColourSprite("a\naa")
    an [IllegalArgumentException] should be thrownBy new SingleColourSprite("aa\na")
    an [IllegalArgumentException] should be thrownBy new SingleColourSprite("aaa\na")
    an [IllegalArgumentException] should be thrownBy new SingleColourSprite("a\na\naa")
    an [IllegalArgumentException] should be thrownBy new SingleColourSprite("aa\naa\na")
  }
  it should "return the correct character" in {
    val spr = new SingleColourSprite("abc\ndef")
    spr.getContent(0, 0) shouldBe 'a'
    spr.getContent(1, 0) shouldBe 'b'
    spr.getContent(2, 0) shouldBe 'c'
    spr.getContent(0, 1) shouldBe 'd'
    spr.getContent(1, 1) shouldBe 'e'
    spr.getContent(2, 1) shouldBe 'f'
  }
  it should "throw an exception when accessing outside the bounds" in {
    val spr = new SingleColourSprite("aaa\naaa")
    an [IndexOutOfBoundsException] should be thrownBy spr.getContent(-1, -1)
    an [IndexOutOfBoundsException] should be thrownBy spr.getContent(-1, 0)
    an [IndexOutOfBoundsException] should be thrownBy spr.getContent(0, -1)
    an [IndexOutOfBoundsException] should be thrownBy spr.getContent(3, 0)
    an [IndexOutOfBoundsException] should be thrownBy spr.getContent(0, 2)
    an [IndexOutOfBoundsException] should be thrownBy spr.getContent(3, 2)
    an [IndexOutOfBoundsException] should be thrownBy spr.getContent(3, -1)
    an [IndexOutOfBoundsException] should be thrownBy spr.getContent(-1, 2)

  }
}
