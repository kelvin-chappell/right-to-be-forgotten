import google.Googler
import org.scalatest.{FlatSpec, Matchers}
import worker.Worker

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class WorkerIntegrationTest extends FlatSpec with Matchers {

//  val urlToTest = "http://www.theguardian.com/artanddesign/2011/aug/30/paris-post-it-wars-french"

  val urlToTest = FetcherTest.getUrl

  "whole backend" should "work right" in {

    val googler = new Googler(GooglerTest.getUrl)
    val blocked = new Worker(googler).getBlockedTerms(List(urlToTest)).toList
    googler.quit
    blocked.length should be (1)
    val first = blocked(0)
    first._1 should be (urlToTest)
    val blockedTerms = first._2
    blockedTerms.length should be (1)
    blockedTerms(0) should be ("Julien Berissi")

  }

}
