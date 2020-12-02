# Advent of Code 2020

My solutions to Advent of Code 2020, this year in Scala (2.13.4), built with sbt (1.4.4).

- [_All days for this edition_](https://github.com/AmauryCarrade/AdventOfCode2020/tree/master/src/main/scala/adventofcode/days)
- [_2019 participation (Rust)_](https://github.com/AmauryCarrade/AdventOfCode2019)

These solutions are published under the CeCILL-B license (BSD-like in French law).

## How to run

Open the sbt shell with `sbt` and run:

```
> day 1
```

where `1` is the day you want.

## How to solve

To add a new solution, create a class named `Day01` (where `01` is the day, of course) following this template.

```scala
package adventofcode.days

import adventofcode.Day

object Day01 extends Day(1) {
  override def firstSolution = ???
  override def secondSolution = ???
}
```

## Thanks

Thanks to F. Cassayre for [inspiration on this project structure](https://github.com/FlorianCassayre/AdventOfCode-2019).
