package fetcher

import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.selenium.WebBrowser
import play.api.Logger

object Fetcher extends WebBrowser {

  implicit val webDriver: WebDriver = new HtmlUnitDriver

  def fetchTextFromURL(URL: String): String = {
    go to (URL)
    if (URL.contains("theguardian")) {
      Logger.info("I am a guardian URL")
      xpath( """//div[@itemprop='articleBody']""").webElement.getText
    }
    else if (URL.contains("dailymail")){
      xpath("""cssSelector("article")""").webElement.getText
    }
    else {
      Logger.info("A log message");
      tagName("body").webElement.getText
    }}


}
