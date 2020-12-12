package adventofcode.days

import adventofcode.Day

object Day06 extends Day(6) {

  val groups = input.split("\n\n").map(_.split("\n"))

  override def firstSolution = groups.map(_.flatten.distinct.length).sum

  override def secondSolution = groups.map(_.map(_.toSet).reduce {
    (answer1, answer2) => answer1.intersect(answer2)
  }).map(_.size).sum
}
