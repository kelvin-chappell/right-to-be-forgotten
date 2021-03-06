package controllers

import google.Googler
import play.api.mvc._
import worker.Worker

object Incremental extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def checkUrl(url: String) = Action {
    val googler = Googler()
    val blocked = new Worker(googler).getBlockedTerms(List(url))
    val result = Ok(views.html.incremental(url, blocked))
    googler.quit
    result
  }
}
