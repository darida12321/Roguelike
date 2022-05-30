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
  it should "fail on incorrect constructor" in {
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

  behavior of "BoxSprite"
  it should "correctly parse the size" in {
    new BoxSprite(1, 1, "aaaaaaa").width shouldBe 1
    new BoxSprite(1, 1, "aaaaaaa").height shouldBe 1
    new BoxSprite(2, 1, "aaaaaaa").width shouldBe 2
    new BoxSprite(2, 1, "aaaaaaa").height shouldBe 1
    new BoxSprite(1, 2, "aaaaaaa").width shouldBe 1
    new BoxSprite(1, 2, "aaaaaaa").height shouldBe 2
    new BoxSprite(3, 3, "aaaaaaa").width shouldBe 3
    new BoxSprite(3, 3, "aaaaaaa").height shouldBe 3
  }
  it should "fail on incorrect constructor" in {
    an [IllegalArgumentException] should be thrownBy new BoxSprite(0, 0, "aaaaaa")
    an [IllegalArgumentException] should be thrownBy new BoxSprite(1, 0, "aaaaaa")
    an [IllegalArgumentException] should be thrownBy new BoxSprite(0, 1, "aaaaaa")
    an [IllegalArgumentException] should be thrownBy new BoxSprite(-1, 1, "aaaaaa")
    an [IllegalArgumentException] should be thrownBy new BoxSprite(1, -1, "aaaaaa")
    an [IllegalArgumentException] should be thrownBy new BoxSprite(0, 0, "aaaaa")
    an [IllegalArgumentException] should be thrownBy new BoxSprite(0, 0, "")
    an [IllegalArgumentException] should be thrownBy new BoxSprite(0, 0, "aaaaaaaa")
  }
  it should "return the correct character for borders" in {
    val spr = new BoxSprite(3, 3, "abcdefg")
    spr.getContent(0, 0) shouldBe 'a'
    spr.getContent(1, 0) shouldBe 'e'
    spr.getContent(2, 0) shouldBe 'b'
    spr.getContent(0, 1) shouldBe 'f'
    spr.getContent(1, 1) shouldBe 'g'
    spr.getContent(2, 1) shouldBe 'f'
    spr.getContent(0, 2) shouldBe 'c'
    spr.getContent(1, 2) shouldBe 'e'
    spr.getContent(2, 2) shouldBe 'd'
  }
  it should "fill the inside with the correct characters" in {
    val spr = new BoxSprite(5, 4, "abcdefg")
    spr.getContent(1, 1) shouldBe 'g'
    spr.getContent(2, 1) shouldBe 'g'
    spr.getContent(3, 1) shouldBe 'g'
    spr.getContent(1, 2) shouldBe 'g'
    spr.getContent(2, 2) shouldBe 'g'
    spr.getContent(3, 1) shouldBe 'g'
  }
  it should "throw an exception when accessing outside the bounds" in {
    val spr = new BoxSprite(3, 2, "abcdefg")
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
