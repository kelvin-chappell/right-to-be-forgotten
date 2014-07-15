import google.Googler
import org.scalatest.{FlatSpec, Matchers}

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class GooglerTest extends FlatSpec with Matchers {

//  val host = "file:///Users/jduffell/ws/right-to-be-forgotten/googlesrc/cheeseSearch.html?q="

  "googler" should "be able to ahnbdle the cheese.com" in {

    val forget = new Googler(GooglerTest.getUrl)
    val newsSite = forget.getResults( "")
    forget.quit
    newsSite should contain ("http://en.wikipedia.org/wiki/Cheese")

  }
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
  "googler" should "be able to ahnbdle the news section" in {

    val forget = new Googler(GooglerTest.getUrlNewsSection)
    val newsSite = forget.getResults( "")
    forget.quit
    newsSite should contain ("http://www.theguardian.com/politics/blog/2014/jul/15/reshuffle-2014-all-the-latest-developments-plus-reaction-and-analysis-politics-live-blog")

  }
  "googler" should "be able to ahnbdle the news section end to end" in {

    val forget = new Googler(GooglerTest.getUrlNewsSection)
    val newsSite = forget.isBlocked("cheese", "http://www.theguardian.com/politics/blog/2014/jul/15/reshuffle-2014-all-the-latest-developments-plus-reaction-and-analysis-politics-live-blog")
    forget.quit
    newsSite should be (false)

  }
}
object GooglerTest {

  def getUrl = getClass.getResource("/cheeseSearch.html").toString + "?q="
  def getUrlNewsSection = getClass.getResource("/googleWithNewsSection.html").toString + "?q="
}