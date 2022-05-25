import scala.collection.Map

sealed trait Command
class Move(d: Direction) extends Command
class Scan(d: Direction) extends Command
object Mine extends Command
class Attack(e: Entity) extends Command // TODO consider weapons?
// TODO add identifiers to map commands to entities

object Commands {
  val COMMANDS: Map[String, ((Entity, List[Any]) => Unit, List[ (String) => Any])] = Map.empty()
}
