package model

import java.io.FileInputStream

import controllers.Application
import opennlp.tools.namefind.{NameFinderME, TokenNameFinderModel}

object NameExtractor {

  lazy val nameFinder = {
    val modelIn = new FileInputStream("conf/en-ner-person.bin")
    val model = new TokenNameFinderModel(modelIn)
    val nameFinder = new NameFinderME(model)
    modelIn.close()
    nameFinder
  }

  def apply(body: String): Seq[String] = {
    val text = Application.testBody.split("[^\\w]")

    val nameSpans = nameFinder.find(text)

    nameSpans map { span =>
      val slice = text.slice(span.getStart, span.getEnd)
      slice.mkString(" ")
    }
  }
}
