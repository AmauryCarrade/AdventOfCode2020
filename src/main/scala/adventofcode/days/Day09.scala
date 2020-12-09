package adventofcode.days

import adventofcode.Day

object Day09 extends Day(9) {
  val preambleSize = 25
  val numbers = lines.map(BigInt(_))
  val badNumber = numbers
    .sliding(preambleSize + 1)
    .filter(numbers => numbers.slice(0, preambleSize).combinations(2).forall(c => c(0) + c(1) != numbers.last))
    .toList.last.last

  val contiguousSet = (2 until lines.size)
    .map(numbers.sliding)
    .map(_.filter(_.sum == badNumber).toList)
    .filter(_.nonEmpty).head.head

  override def firstSolution = badNumber
  override def secondSolution = contiguousSet.min + contiguousSet.max
}
