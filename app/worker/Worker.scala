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
  type Blocked = Boolean

  private def firstBlockedName(url: ArticleURL, names: Stream[SearchTerm]): Stream[(SearchTerm, Blocked)] = {
    val streamOfBlockages = names.map(name => (name, googler.isBlocked(name, url)))
      streamOfBlockages.takeWhile(_._2 == false).append(streamOfBlockages.find(_._2 == true))
  }

  def getBlockedTerms(urls: List[ArticleURL]): Stream[Stream[(SearchTerm, Blocked)]] = {
    val urlText = urls.toStream.map(url => (url, Fetcher.fetchTextFromURL(url)))
    val names = urlText.map { case (url, body) => (url, NameExtractor(body)) }
    names.map { case (url, names) => firstBlockedName(url, names.toStream)}
  }

}
object Worker {
  def apply = {
    new Worker(Googler())
  }
}