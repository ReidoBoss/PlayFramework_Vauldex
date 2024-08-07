package controllers

import play.api.mvc._
import java.util.UUID
import javax.inject._

@Singleton
class BookController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def search(title: Option[String], author: Option[String]) = Action { implicit request: Request[AnyContent] =>
    Ok("Search books by title or author")
  }

  def list = Action { implicit request: Request[AnyContent] =>
    Ok("List of all available books")
  }

  def borrow(id: UUID) = Action { implicit request: Request[AnyContent] =>
    Ok(s"Borrow book with ID: $id")
  }

  def returnBook(id: UUID) = Action { implicit request: Request[AnyContent] =>
    Ok(s"Return book with ID: $id")
  }
}
