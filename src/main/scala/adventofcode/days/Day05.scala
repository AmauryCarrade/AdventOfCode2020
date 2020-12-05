package adventofcode.days

import adventofcode.Day

object Day05 extends Day(5) {

  private def seatPosition(seat: String) = seat.foldLeft((0, 0)) { case ((row, col), c) => c match {
      case 'F' => (row << 1, col)
      case 'B' => ((row << 1) | 1, col)
      case 'L' => (row, col << 1)
      case 'R' => (row, (col << 1) | 1)
    }
  }

  private def seatID(seat: (Int, Int)) = seat._1 * 8 + seat._2

  override def firstSolution = lines.map(seatPosition).map(seatID).max
  override def secondSolution = lines.map(seatPosition).map(seatID).sorted.sliding(2).filter(s => s(1) - s(0) != 1).toList.head(1) - 1

  // We get a sorted list of all seat IDs, then compute a list of pairs in this list (with sliding(2)), then extract
  // when the gap is not 1.
}
