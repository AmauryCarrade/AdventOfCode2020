package adventofcode.days

import adventofcode.Day

object Day12 extends Day(12) {

  case class Vec(x: Float, y: Float) {
    def +(v: Vec): Vec = Vec(x + v.x, y + v.y)
    def -(v: Vec): Vec = Vec(x - v.x, y - v.y)
    def *(v: Vec): Float = x * v.x + y * v.y
    def *(f: Float): Vec = Vec(x * f, y * f)
    def /(f: Float): Vec = Vec(x / f, y / f)
    def unary_- : Vec = Vec(-x, -y)

    def manhattan(v: Vec): Float = (v.x - x).abs + (v.y - y).abs

    def rotate(angle: Int): Vec = angle match {
      case 0 => this
      case 90 => Vec(y, -x)
      case 180 => Vec(-x, -y)
      case 270 => Vec(-y, x)
    }
  }

  object Vec {
    def apply() : Vec = Vec(0, 0)
  }

  val EAST = Vec(0, 1)
  val WEST = Vec(0, -1)
  val NORTH = Vec(-1, 0)
  val SOUTH = Vec(1, 0)

  case class Movement(direction: Vec, location: Vec) {
    def +(m: Movement): Movement = Movement(m.direction, location + m.location)
    def +(v: Vec): Movement = Movement(direction, location + v)
    def +(l: Int): Movement = Movement(direction, location + direction * l)

    def rotate(angle: Int): Movement = Movement(direction.rotate(angle), location)
  }

  case class Instruction(action: Char, value: Int)

  override def firstSolution = lines
    .map(i => Instruction(i(0), i.drop(1).toInt))
    .foldLeft(Movement(EAST, Vec())) {
      (movement, instruction) => instruction.action match {
        case 'F' => movement + instruction.value
        case 'N' => movement + NORTH * instruction.value
        case 'S' => movement + SOUTH * instruction.value
        case 'E' => movement + EAST * instruction.value
        case 'W' => movement + WEST * instruction.value
        case 'R' => movement.rotate(instruction.value)
        case 'L' => movement.rotate(360 - instruction.value)
        case _ => movement
      }
    }
    .location.manhattan(Vec()).toInt

  override def secondSolution = lines
    .map(i => Instruction(i(0), i.drop(1).toInt))
    .foldLeft((Vec(), Vec(-1, 10))) {
      case ((ship, waypoint), instruction) => instruction.action match {
          case 'F' => (ship + (waypoint - ship) * instruction.value, waypoint + (waypoint - ship) * instruction.value)
          case 'N' => (ship, waypoint + NORTH * instruction.value)
          case 'S' => (ship, waypoint + SOUTH * instruction.value)
          case 'E' => (ship, waypoint + EAST * instruction.value)
          case 'W' => (ship, waypoint + WEST * instruction.value)
          case 'R' => (ship, (waypoint - ship).rotate(instruction.value)+ ship)
          case 'L' => (ship, (waypoint - ship).rotate(360 - instruction.value) + ship)
        }
    }
    ._1.manhattan(Vec()).toInt
}
