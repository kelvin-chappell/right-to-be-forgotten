import model.NameExtractor
import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class NameExtractorTest extends FlatSpec with Matchers {

//  val host = "file:///Users/jduffell/ws/right-to-be-forgotten/googlesrc/cheeseSearch.html?q="
  def body = Source.fromURL(getClass.getResource("/guardianArticle.html")).getLines().mkString

  "googler" should "work right" in {

    val names = NameExtractor(body)
    println("text is: " + names.toList)
    body should not be ("")

  }

}
