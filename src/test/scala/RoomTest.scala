import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class RoomTest extends AnyFlatSpec {

  behavior of "Room connections"
  it should "initialize with no connections" in {
    val room = new Room(1, 1, 1)
    room.roomAt(Right) shouldBe None
    room.roomAt(Up) shouldBe None
    room.roomAt(Left) shouldBe None
    room.roomAt(Down) shouldBe None
  }

  it should "add new connections in different directions" in {
    val room = new Room(1, 1, 1)
    val room1 = new Room(1, 1, 2)
    val room2 = new Room(1, 1, 3)
    val room3 = new Room(1, 1, 4)
    val room4 = new Room(1, 1, 5)
    
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
    val room = new Room(1, 1, 1)
    val room1 = new Room(1, 1, 2)
    val room2 = new Room(1, 1, 3)
    val room3 = new Room(1, 1, 4)
    val room4 = new Room(1, 1, 5)

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

  behavior of "Room entity set"
  it should "start with no entities" in {
    val room = new Room(1, 1, 1)
    room.getEntities().size shouldBe 0
  }

  it should "keep track of added entities" in {
    val room = new Room(1, 1, 1)
    val e1 = new TestEntity('a', true)
    val e2 = new TestEntity('b', true)
    room.addEntity(e1)
    room.getEntities() shouldBe Set(e1)
    room.addEntity(e2)
    room.getEntities() shouldBe Set(e1, e2)
  }

  it should "throw an error when adding an entity twice" in {
    val room = new Room(1, 1, 1)
    val e = new TestEntity('a', true)
    room.addEntity(e)
    room.getEntities() shouldBe Set(e)
    an [IllegalArgumentException] should be thrownBy room.addEntity(e)
  }

  it should "remove entities" in {
    val room = new Room(1, 1, 1)
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
    val room = new Room(1, 1, 1)
    val e = new TestEntity('a', true)
    an [IllegalArgumentException] should be thrownBy room.removeEntity(e)
  }

  behavior of "The room variable of entities"
  it should "start with no rooms present" in {
    val e = new TestEntity('a', true)
    e.getRoom() shouldBe None
  }
  
  it should "update when entering a room" in {
    val room = new Room(1, 1, 1)
    val e = new TestEntity('a', true)
    room.addEntity(e)
    e.getRoom() shouldBe Some(room)
  }

  it should "update when leaving a room" in {
    val room = new Room(1, 1, 1)
    val e = new TestEntity('a', true)
    room.addEntity(e)
    room.removeEntity(e)
    e.getRoom() shouldBe None
  }

  it should "throw an error when in two rooms at once" in {
    val room1 = new Room(1, 1, 1)
    val room2 = new Room(1, 1, 2)
    val e = new TestEntity('a', true)
    room1.addEntity(e)
    an [IllegalArgumentException] should be thrownBy room2.addEntity(e)
  }

  it should "not be allowed to be changed" in {
    val room = new Room(1, 1, 1)
    val e = new TestEntity('a', true)
    an [IllegalArgumentException] should be thrownBy e.setRoom(room)
    an [IllegalArgumentException] should be thrownBy e.setRoom(Some(room))
  }

  behavior of "moveRoom funciton for entities"
  it should "add entity to entity set when an entity moves into the room" in {
    val room = new Room(1, 1, 1)
    val e = new TestEntity('a', true)
    e.moveRoom(room)
    room.containsEntity(e) shouldBe true
  }

  it should "remove entity from entity set when entity leaves the room" in {
    val room = new Room(1, 1, 1)
    val e = new TestEntity('a', true)
    e.moveRoom(room)
    e.moveRoom(None)
    room.containsEntity(e) shouldBe false
  }

  it should "update the room variable when moving between rooms" in {
    val room1 = new Room(1, 1, 1)
    val room2 = new Room(1, 1, 1)
    val e = new TestEntity('a', true)
    e.moveRoom(room1)
    e.moveRoom(room2)
    e.getRoom() shouldBe Some(room2)
  }

  it should "reset the room variable when moving out of room" in {
    val room = new Room(1, 1, 1)
    val e = new TestEntity('a', true)
    e.moveRoom(room)
    e.moveRoom(None)
    e.getRoom() shouldBe None
  }
}

// displaySelf
//
// rooms initialized entities belong to that room
// rooms initialized without entities given
