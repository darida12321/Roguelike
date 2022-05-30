import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class RoomTest extends AnyFlatSpec {
  val room = new Room(1, 1, 1, Set.empty)
  val room1 = new Room(1, 1, 2, Set.empty)
  val room2 = new Room(1, 1, 3, Set.empty)
  val room3 = new Room(1, 1, 4, Set.empty)
  val room4 = new Room(1, 1, 5, Set.empty)

  behavior of "Room connections"
  it should "initialize with no connections" in {
    room.roomAt(Right) shouldBe None
    room.roomAt(Up) shouldBe None
    room.roomAt(Left) shouldBe None
    room.roomAt(Down) shouldBe None
  }
  it should "add new connections in different directions" in {
    room.connect(Right, room1)
    room.connect(Up, room2)
    room.connect(Left, room3)
    room.connect(Down, room4)

    room.roomAt(Right) shouldBe Some(room1)
    room.roomAt(Up) shouldBe Some(room2)
    room.roomAt(Left) shouldBe Some(room3)
    room.roomAt(Down) shouldBe Some(room4)
  }
  it should "make the other room reconnect in correct direction" in {
    room.connect(Right, room1)
    room.connect(Up, room2)
    room.connect(Left, room3)
    room.connect(Down, room4)

    room1.roomAt(Left) shouldBe Some(room)
    room2.roomAt(Down) shouldBe Some(room)
    room3.roomAt(Right) shouldBe Some(room)
    room4.roomAt(Up) shouldBe Some(room)
  }



}

// roomAt
// connect
// addEntity
// removeEntity
// containsEntity
// displaySelf
