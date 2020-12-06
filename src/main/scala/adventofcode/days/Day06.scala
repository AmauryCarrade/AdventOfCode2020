package adventofcode.days

import adventofcode.Day

object Day06 extends Day(6) {
  private def parse(lines: Seq[String]) = (lines :+ "").foldLeft((Seq[String](), Seq[Seq[String]]())) {
    case ((current, all), line) => line match {
      case "" => (Seq[String](), all :+ current)
      case _ => (current :+ line, all)
    }
  }._2

  override def firstSolution = parse(lines).map(_.flatten.toSet.size).sum

  override def secondSolution = parse(lines).map(_.foldLeft(null: Set[Char]) {
    case (intersect, answer) => intersect match {
      case null => answer.toSet
      case _ => intersect.intersect(answer.toSet)
    }
  }).map(_.size).sum
}
