package model

object NameExtractor {

  def apply(body: String): Seq[String] = {
    val regex = """\b[A-Z][a-z]+\s+\b[A-Z][a-z]+""".r

    regex.findAllIn(body).toSeq
  }
}
