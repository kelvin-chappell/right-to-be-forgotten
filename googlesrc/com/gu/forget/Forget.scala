package com.gu.forget

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest.selenium.WebBrowser

/**
 * Created by jduffell on 14/07/2014.
 */
class Forget extends WebBrowser {

  implicit val webDriver: WebDriver = new ChromeDriver

//  val host = "https://www.google.co.uk/search?btnG=1&gws_rd=ssl&q="
  val host = "file:///Users/jduffell/ws/right-to-be-forgotten/googlesrc/cheeseSearch.html"

  private def getUrlFromResult(element: Element) = {
    element.attribute("data-href").orElse(element.attribute("href"))
  }

  def isBlocked(terms: String): Boolean = {
    go to (host+terms.replaceAll(" ", "+") )
    println(s"title: $pageTitle")
    val results = findAll(xpath("//li[@class='g']//h3[@class='r']/a")).toList
    println("length: " + results.length)
    val urls = results.map(getUrlFromResult)
    println("urls: " + urls)
    false
  }

  def quit = {
    super.quit
  }

}

object Forget {

  def main(args: Array[String]) {
    val forget = new Forget()
      forget.isBlocked("")
    forget.quit
  }

}