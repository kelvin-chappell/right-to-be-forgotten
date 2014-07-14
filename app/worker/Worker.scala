package worker

/**
 * Created by jduffell on 14/07/2014.
 */
object Worker {

  type ArticleURL = String
  type SearchTerm = String

  def getBlockedTerms(urls: List[String]): Stream[(ArticleURL,Stream[SearchTerm])] = ???

}
