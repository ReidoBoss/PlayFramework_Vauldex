package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._

case class Book(id: Long, title: String, author: String)
object Book {
  implicit val bookFormat = Json.format[Book]
}

@Singleton
class BookController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private var books = List(
    Book(1, "1984", "George Orwell"),
    Book(2, "Brave New World", "Aldous Huxley")
  )

  def getBooks: Action[AnyContent] = Action {
    Ok(Json.toJson(books))
  }

  def addBook: Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Book].map { book =>
      books = books :+ book
      Created(Json.toJson(book))
    }.getOrElse(BadRequest("Invalid book format"))
  }

  def getBook(id: Long): Action[AnyContent] = Action {
    books.find(_.id == id) match {
      case Some(book) => Ok(Json.toJson(book))
      case None => NotFound("Book not found")
    }
  }

  def updateBook(id: Long): Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Book].map { updatedBook =>
      books.find(_.id == id) match {
        case Some(book) =>
          books = books.map(b => if (b.id == id) updatedBook else b)
          Ok(Json.toJson(updatedBook))
        case None => NotFound("Book not found")
      }
    }.getOrElse(BadRequest("Invalid book format"))
  }

  def deleteBook(id: Long): Action[AnyContent] = Action {
    books.find(_.id == id) match {
      case Some(book) =>
        books = books.filterNot(_.id == id)
        NoContent
      case None => NotFound("Book not found")
    }
  }
}
