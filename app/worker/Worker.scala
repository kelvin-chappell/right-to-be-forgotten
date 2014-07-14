package worker

import google.Googler

/**
 * Created by jduffell on 14/07/2014.
 */
class Worker(googler: Googler) {

  type ArticleURL = String
  type SearchTerm = String

  def getBlockedTerms(urls: List[String]): Stream[(ArticleURL,Stream[SearchTerm])] = ???

}
object Worker {
  def apply = {
    new Worker(Googler())
  }
}