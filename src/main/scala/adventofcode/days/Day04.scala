package adventofcode.days

import adventofcode.Day

import scala.collection.immutable.HashMap

object Day04 extends Day(4) {
  val sep = raw"[ \n]+".r
  val passports = (lines :+ "")
    .map(sep split _)
    .foldLeft((Seq[HashMap[String, String]](), HashMap[String, String]())) {
      case ((passports, current), line) => if (line.length == 1 && line.head.isEmpty) {
          (passports :+ current, HashMap())
        } else {
          (passports, current ++ line.map(_ split ":").map(v => v(0) -> v(1)))
        }
    }._1.toSeq

  private def checkPassportFields(passport: HashMap[String, String]) = passport.size match {
    case 8 => true
    case 7 => !passport.contains("cid")
    case _ => false
  }

  val valuesCheck = raw"byr,(\d{4})(?:;cid,\d+)?;ecl,(?:amb|blu|brn|gry|grn|hzl|oth);eyr,(\d{4});hcl,#[a-f0-9]{6};hgt,(\d{2,3})(in|cm);iyr,(\d{4});pid,\d{9}".r

  private def checkPassportValues(passport: HashMap[String, String]) = {
     passport.toSeq.sortBy(_._1).map(s => s._1 + "," + s._2).mkString(";") match {
       case valuesCheck(byr, eyr, hgt, hgt_unit, iyr) => (
         (1920 to 2002 contains byr.toInt)
         && (2010 to 2020 contains iyr.toInt)
         && (2020 to 2030 contains eyr.toInt)
         && ((hgt_unit == "cm" && (150 to 193 contains hgt.toInt)) || (59 to 76 contains hgt.toInt))
       )
       case _ => false
     }
  }

  override def firstSolution = passports.filter(checkPassportFields).size
  override def secondSolution = passports.filter(checkPassportFields).filter(checkPassportValues).size
}
