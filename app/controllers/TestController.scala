package controllers

import javax.inject._
import play.api.mvc._
import request.query.Cells

@Singleton
class TestController @Inject() extends Controller {
  def index(cells: Cells) = Action {
    Ok(views.html.index(cells))
  }
}
