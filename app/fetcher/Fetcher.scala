package fetcher

import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.selenium.WebBrowser

object Fetcher extends WebBrowser {

  implicit val webDriver: WebDriver = new HtmlUnitDriver

  def fetchTextFromURL(URL: String): String = {
    go to (URL)
    tagName("body").webElement.getText
  }

}
