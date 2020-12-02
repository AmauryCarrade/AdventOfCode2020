package adventofcode.days

import adventofcode.Day


object Day01 extends Day(1) {

  implicit class Crossable[X](xs: Iterable[X]) {
    def cross[Y](ys: Iterable[Y]): Iterable[(X, Y)] = for {x <- xs; y <- ys} yield (x, y)
  }

  val numbers = lines.map(_.toInt)

  override def firstSolution = (numbers cross numbers).iterator.filter(l => l._1 + l._2 == 2020).map(l => l._1 * l._2).toSeq.head

  override def secondSolution = (numbers cross numbers cross numbers).iterator
    .map(l => (l._1._1, l._1._2, l._2))  // flatten tuple in tuple
    .filter(l => l._1 + l._2 + l._3 == 2020).map(l => l._1 * l._2 * l._3).toSeq.head
}
