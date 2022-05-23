trait Entity {
  val char: Char 
  val colour: String
  val visible: Boolean
}

class TestEntity(val char: Char, val colour: String) extends Entity {
  val visible = true
}
class TestEntity2(val char: Char, val colour: String) extends Entity {
  val visible = false 
}

class Robot() extends Entity {
  val char = 'A'
  val colour = Console.GREEN
  val visible = true
}
