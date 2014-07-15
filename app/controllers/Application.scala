package controllers

import google.Googler
import model.NameExtractor
import play.api.mvc._
import play.api.libs.ws.WS
import scala.concurrent.ExecutionContext.Implicits.global

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def checkUrl(url: String) = Action.async {
    val googler = Googler.apply()// FIXME never closed!!

    WS.url(url).get() map { response =>
      val body = response.body
      val names = NameExtractor(body)
      val blocked = names find { name =>
        googler.isBlocked(name, url)
      }
      val result = blocked map (name => s"This name was blocked: $name") getOrElse "No results"
      Ok(result)
    }
  }
}
