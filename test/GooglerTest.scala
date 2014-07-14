import google.Googler
import org.scalatest.{FlatSpec, Matchers}

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class GooglerTest extends FlatSpec with Matchers {

//  val host = "file:///Users/jduffell/ws/right-to-be-forgotten/googlesrc/cheeseSearch.html?q="

  "googler" should "work right" in {

    val forget = new Googler(GooglerTest.getUrl)
    val cheeseBlocked = forget.isBlocked("cheese", "http://www.cheese.com/")
    forget.quit
    cheeseBlocked should be (false)

  }
  "googler" should "work right2" in {

    val forget = new Googler(GooglerTest.getUrl)
    val baddieBlocked = forget.isBlocked("cheese", "http://www.baddie.com/")
    forget.quit
    baddieBlocked should be (true)

  }
}
object GooglerTest {

  def getUrl = getClass.getResource("/cheeseSearch.html").toString + "?q="
}