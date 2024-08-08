package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController {

  val test = Future.successful(1)
  val meow = Future.successful(2)
  def index() = Action.async { implicit request: Request[AnyContent] =>
    meow.map{
      x=> 
        Ok(x.toString)
    }
  }
}

