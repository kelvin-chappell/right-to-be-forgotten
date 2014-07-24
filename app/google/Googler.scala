package google

import java.net.URLEncoder

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest.selenium.WebBrowser

/**
 * Created by jduffell on 14/07/2014.
 *
 * Just call isBlocked
 */
class Googler(host: String) extends WebBrowser {

  implicit val webDriver: WebDriver = new ChromeDriver

  private def getUrlFromResult(element: Element) = {
    element.attribute("data-href").orElse(element.attribute("href")).get
  }

  def isBlocked(terms: String, articleUrl: String): Boolean = {
    val results = getResults(terms + " site:theguardian.com "+ articleUrl)
    println(s"looking for <$articleUrl> in (${results.mkString(", ")})")
    val blocked = !results.contains(articleUrl)
    println(s"is blocked: $blocked")
    blocked
  }

  def getResults(terms: String): List[String] = {
    go to (host+URLEncoder.encode(terms, "UTF-8"))
    println(s"title: $pageTitle")
    println(s"currentUrl: $currentUrl")
    val results = findAll(xpath("//li[@class='g']//a")).toList
    println("length: " + results.length)
    val urls = results.map(getUrlFromResult)
    println("urls: " + urls)
    urls
  }

  def quit = {
    super.quit
  }

}

object Googler {
  val host = "https://www.google.co.uk/search?btnG=1&gws_rd=ssl&q="
  def apply() = new Googler(host)
}