package controllers

import model.NameExtractor
import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    NameExtractor("abc Kelvin def Kelvin Chappell jjlkj") foreach println
    Ok(views.html.index("Your new application is ready."))
  }

}
