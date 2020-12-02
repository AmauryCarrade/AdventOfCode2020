package adventofcode.days

import adventofcode.Day

object Day02 extends Day(2) {

  val regex = raw"(\d+)-(\d+) ([a-z]): ([a-z]+)".r
  val parsed = lines.map {
    case regex(min, max, letter, password) => (min.toInt, max.toInt, letter.head, password)
  }

  override def firstSolution = parsed.count { case (min, max, letter, password) =>
    val count = password.count(_ == letter)
    min <= count && count <= max
  }

  override def secondSolution = parsed.count { case (min, max, letter, password) =>
    password(min - 1) == letter ^ password(max - 1) == letter
  }
}
