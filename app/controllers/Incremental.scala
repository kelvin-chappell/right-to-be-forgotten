package controllers

import google.Googler
import play.api.mvc._
import worker.Worker

object Incremental extends Controller {

  val googler = Googler.apply()

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def checkUrl(url: String) = Action {
    val blocked = new Worker(Googler()).getBlockedTerms(List(url))
    Ok(views.html.incremental(blocked))
  }
}
