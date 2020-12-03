package adventofcode.days

import adventofcode.Day

object Day03 extends Day(3) {

  val mapExtractWidth = lines.head.length


  private def treeAt(x: Int, y: Int) = lines(x)(y % mapExtractWidth) == '#'

  private def treesOnSlope(stepX: Int, stepY: Int) = (0 until lines.length / stepX)
    .count(l => treeAt(l * stepX, l * stepY))


  override def firstSolution = treesOnSlope(1, 3)

  override def secondSolution = Seq((1, 1), (1, 3), (1, 5), (1, 7), (2, 1))
    .map(slope => treesOnSlope(slope._1, slope._2))
    .foldLeft(BigInt(1)) { _ * _ }
}
