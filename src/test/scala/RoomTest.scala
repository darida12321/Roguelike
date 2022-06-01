import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class RoomTest extends AnyFlatSpec {

  behavior of "Room connections"
  it should "initialize with no connections" in {
    val room = new Room(1, 1, 1, Set.empty)
    room.roomAt(Right) shouldBe None
    room.roomAt(Up) shouldBe None
    room.roomAt(Left) shouldBe None
    room.roomAt(Down) shouldBe None
  }
  it should "add new connections in different directions" in {
    val room = new Room(1, 1, 1, Set.empty)
    val room1 = new Room(1, 1, 2, Set.empty)
    val room2 = new Room(1, 1, 3, Set.empty)
    val room3 = new Room(1, 1, 4, Set.empty)
    val room4 = new Room(1, 1, 5, Set.empty)
    
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
    val room = new Room(1, 1, 1, Set.empty)
    val room1 = new Room(1, 1, 2, Set.empty)
    val room2 = new Room(1, 1, 3, Set.empty)
    val room3 = new Room(1, 1, 4, Set.empty)
    val room4 = new Room(1, 1, 5, Set.empty)

    room.connect(Right, room1)
    room.connect(Up, room2)
    room.connect(Left, room3)
    room.connect(Down, room4)

    room1.roomAt(Left) shouldBe Some(room)
    room2.roomAt(Down) shouldBe Some(room)
    room3.roomAt(Right) shouldBe Some(room)
    room4.roomAt(Up) shouldBe Some(room)
  }

  class TestEntity(val char: Char, val visible: Boolean) extends Entity {
    val colour = Console.RESET
  }

  behavior of "Room entities"
  it should "start with no entities" in {
    val room = new Room(1, 1, 1, Set.empty)
    room.getEntities().size shouldBe 0
  }
  it should "keep track of added entities" in {
    val room = new Room(1, 1, 1, Set.empty)
    val e1 = new TestEntity('a', true)
    val e2 = new TestEntity('b', true)
    room.addEntity(e1)
    room.getEntities() shouldBe Set(e1)
    room.addEntity(e2)
    room.getEntities() shouldBe Set(e1, e2)
  }
  it should "throw an error when adding an entity twice" in {
    val room = new Room(1, 1, 1, Set.empty)
    val e1 = new TestEntity('a', true)
    room.addEntity(e1)
    room.getEntities() shouldBe Set(e1)
    an [IllegalArgumentException] should be thrownBy room.addEntity(e1)
  }
  it should "remove entities" in {
    val room = new Room(1, 1, 1, Set.empty)
    val e1 = new TestEntity('a', true)
    val e2 = new TestEntity('b', true)
    room.addEntity(e1)
    room.addEntity(e2)
    room.getEntities() shouldBe Set(e1, e2)
    room.removeEntity(e1)
    room.getEntities() shouldBe Set(e2)
    room.removeEntity(e2)
    room.getEntities() shouldBe Set()
  }
  it should "throw an error when removing non-existent entities" in {
    val room = new Room(1, 1, 1, Set.empty)
    val e1 = new TestEntity('a', true)
    an [IllegalArgumentException] should be thrownBy room.removeEntity(e1)
  }


}

// addEntity
// removeEntity
// containsEntity
// displaySelf
