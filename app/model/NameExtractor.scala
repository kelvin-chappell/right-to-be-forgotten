package model

object NameExtractor {

  def apply(body: String): Seq[String] = {
    val regex = """\b[A-Z][a-z]+\s+\b[A-Z][A-Za-z]+""".r

    regex.findAllIn(body).toSeq
  }
}
