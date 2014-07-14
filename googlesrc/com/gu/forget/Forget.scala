package com.gu.forget

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest.selenium.WebBrowser

/**
 * Created by jduffell on 14/07/2014.
 */
class Forget extends WebBrowser {

  implicit val webDriver: WebDriver = new ChromeDriver

  val host = "http://www.google.co.uk"

  def isBlocked: Boolean = {
    go to (host )
    println(s"title: $pageTitle")
    false
  }

  def quit = {
    super.quit
  }

}

object Forget {

  def main(args: Array[String]) {
    val forget = new Forget()
      forget.isBlocked
    forget.quit
  }

}