package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{Future,ExecutionContext}

class LoggingAction @Inject() (parser: BodyParsers.Default)(implicit ec: ExecutionContext)
    extends ActionBuilderImpl(parser){
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    println("meow"+request)
    block(request)
  }
}