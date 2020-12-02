package adventofcode

import scala.io.Source
import scala.util.{Failure, Success, Try}

abstract class Day(val day: Int) extends App {
  require(day >= 1 && day <= 25, "Day out of range")

  private val inputPath = f"input/day-$day%02d.txt"

  private def readInput() = Source.fromFile(inputPath).getLines().mkString("\n")

  protected val input: String = readInput()
  protected val lines: IndexedSeq[String] = input.split("\n").toIndexedSeq

  def firstSolution: Any

  def secondSolution: Any

  private final def render(): Unit = {
    println(s"Solutions for day $day")
    renderSolution("First problem", firstSolution)
    renderSolution("Second problem", secondSolution)
  }

  private final def renderSolution(name: String, solution: => Any): Unit = {
    println(s"| $name")
    Try(solution.toString) match {
      case Success(value) => println(s"|  $value")
      case Failure(_: NotImplementedError) => println("|  (no solution for now)")
      case Failure(exception) => throw new RuntimeException(exception)
    }
  }

  private val mainThread: Thread = Thread.currentThread()
  new Thread(() => {
    mainThread.join()
    render()
  }).start()
}
