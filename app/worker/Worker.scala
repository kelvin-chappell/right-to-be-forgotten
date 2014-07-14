package worker

import fetcher.Fetcher
import google.Googler
import model.NameExtractor

/**
 * Created by jduffell on 14/07/2014.
 */
class Worker(googler: Googler) {

  type ArticleURL = String
  type SearchTerm = String

  def streamOfBlockedNames(url: ArticleURL, names: Stream[String]): Option[String] = {
    names.filter(name => googler.isBlocked(url, name)).headOption
  }

  def getBlockedTerms(urls: List[String]): Stream[(ArticleURL,Option[SearchTerm])] = {
    val urlText = urls.toStream.map(url => (url, Fetcher.fetchTextFromURL(url)))
    val names = urlText.map { case (url, body) => (url, NameExtractor(body)) }
    names.map { case (url, names) => (url, streamOfBlockedNames(url, names.toStream))}
  }

}
object Worker {
  def apply = {
    new Worker(Googler())
  }
}