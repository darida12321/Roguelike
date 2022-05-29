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
}
