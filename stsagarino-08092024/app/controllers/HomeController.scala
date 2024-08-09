package controllers
import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class HomeController @Inject()(authorizedAction: AuthorizedAction, val controllerComponents: ControllerComponents)(implicit val ec: ExecutionContext) extends BaseController {
  def createSession = Action.async { implicit request =>
    Future.successful(Ok.withSession("email" -> "user@gmail.com"))
  }
  def home = authorizedAction.async { implicit request =>
    Future.successful(Ok("You are logged in"))
  }
}