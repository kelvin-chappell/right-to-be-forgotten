package model

import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations.{SentencesAnnotation, TokensAnnotation}
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}

import scala.collection.JavaConversions._
import scala.collection.mutable

object NameExtractor {

  val pipeline = {
    val props = new Properties()
    props.put("annotators", "tokenize, ssplit, pos, lemma, ner")
    new StanfordCoreNLP(props)
  }

  def apply(body: String): Seq[String] = {

    val document = new Annotation(body)
    pipeline.annotate(document)

    var names = mutable.Seq[String]()

    val sentences = document.get(classOf[SentencesAnnotation])

    var inName = false

    sentences foreach { sentence =>
      val tokens = sentence.get(classOf[TokensAnnotation])
      tokens foreach { token =>

        println(token.ner() + ": " + token.value())

        if (token.ner() == "PERSON") {
          if (inName) {
            names.update(names.size - 1, names(names.size - 1) + " " + token.value())
          } else {
            names = names :+ token.value()
            inName = true
          }
        } else {
          inName = false
        }
      }
    }

    names.distinct
  }
}
