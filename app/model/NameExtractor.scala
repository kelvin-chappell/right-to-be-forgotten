package model

import scala.io.Source

object NameExtractor {

  val regex = """\b[A-Z][a-z]+\s+\b[A-Z][A-Za-z]+""".r

  val common = Set("A", "As", "The")

  lazy val firstNames = Source.fromURL(getClass.getResource("/firstNames.csv")).getLines.toSeq.tail.toSet
  lazy val lastNames = Source.fromURL(getClass.getResource("/lastNames.csv")).getLines.toSeq.tail.toSet

  def apply(body: String): Seq[String] = {

    val firstPass = regex.findAllIn(body).toSeq

    val secondPass = firstPass filterNot { name =>
      val start = name.split("\\b").head
      common contains start
    }

    val (recognisedNames, unrecognisedNames) = secondPass partition { name =>
      val start = name.split("\\s").head
      val lastName = name.split("\\s").last
      lastNames.contains(lastName) && firstNames.contains(start)
    }

    (recognisedNames ++ unrecognisedNames).distinct
  }
}
