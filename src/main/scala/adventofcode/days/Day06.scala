package adventofcode.days

import adventofcode.Day

object Day06 extends Day(6) {

  val groups = input.split("\n\n").map(_.split("\n"))

  override def firstSolution = groups.map(_.flatten.distinct.length).sum

  override def secondSolution = groups.map(_.foldLeft(null: Set[Char]) {
    case (intersect, answer) => intersect match {
      case null => answer.toSet
      case _ => intersect.intersect(answer.toSet)
    }
  }).map(_.size).sum
}
