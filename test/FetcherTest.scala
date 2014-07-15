import fetcher.Fetcher
import org.scalatest.{FlatSpec, Matchers}

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class FetcherTest extends FlatSpec with Matchers {

//  val host = "file:///Users/jduffell/ws/right-to-be-forgotten/googlesrc/cheeseSearch.html?q="
  "fetcher" should "work right" in {

    val fetcher = Fetcher
    val body = fetcher.fetchTextFromURL(FetcherTest.getUrl)
    println("text is: " + body)
    body should not be ("")

  }

}
object FetcherTest {

  def getUrl = getClass.getResource("/gregorySim.html").toString + "?q="

}