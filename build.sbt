name := "adventofcode"
version := "0.1"
scalaVersion := "2.13.4"

// MIT license by Florian Cassayre — https://github.com/FlorianCassayre/AdventOfCode-2019
commands += Command("day") { _ =>
  import complete.DefaultParsers._
  (' ' ~ charClass(_.isDigit, "digit").+.map(_.mkString.toInt)).map(_._2)
} { case (previousState, i: Int) =>
  val formatted = "%02d".format(i)
  Command.process(s"runMain adventofcode.days.Day$formatted", previousState)
}
