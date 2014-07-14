package fetcher

import org.scalatest.selenium.WebBrowser

class Fetcher extends WebBrowser {

  def fetchTextFromURL(URL: String): String = {
    go to (URL)
    tagName("body").webElement.getText
  }

}
