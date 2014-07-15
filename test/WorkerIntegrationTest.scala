import google.Googler
import org.scalatest.{FlatSpec, Matchers}
import worker.Worker

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class WorkerIntegrationTest extends FlatSpec with Matchers {

  "whole backend" should "work right" in {

    val urlToTest = FetcherTest.getUrl

    val googler = new Googler(GooglerTest.getUrl)
    val blocked = new Worker(googler).getBlockedTerms(List(urlToTest)).toList
    blocked.length should be (1)
    val first = blocked(0)
    first.find(_._2).get._1 should be ("Michael Gove")
    googler.quit // TODO in teardown

  }

//  "whole backend" should "work right against live" in {
//
//    val urlToTest = "http://www.theguardian.com/artanddesign/2011/aug/30/paris-post-it-wars-french"
//
//    val googler = Googler()
//    val blocked = new Worker(googler).getBlockedTerms(List(urlToTest)).toList
//    blocked.length should be (1)
//    val first = blocked(0)
//    first.get should be ("Julien Berissi")
//    googler.quit // TODO in teardown
//
//  }

}
