package controllers
import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{Future,ExecutionContext}

class AuthorizedAction @Inject() (parser: BodyParsers.Default)(implicit ec: ExecutionContext)
    extends ActionBuilderImpl(parser) with InjectedController{
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result])={
	request.session.get("email").map {email=>block(request)}.getOrElse(Future(Unauthorized))
  
	}
}