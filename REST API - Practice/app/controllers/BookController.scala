package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._

case class Book(id: Long, title: String, author: String)

object Book {
  implicit val bookFormat:OFormat[Book] = Json.format[Book]
}

def bookSerializer(book:Book):String = {
  s"""{"id":${book.id}, "title": "${book.title}", "author": "${book.author}"}"""
}

def listSerializer(arr:List[Book])=
  arr.map{x=>bookSerializer(x)}.mkString(", ")


@Singleton
class BookController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
// 
  private var books = List(
    Book(1, "1984", "George Orwell"),
    Book(2, "Brave New World", "Aldous Huxley")
  )


  def getBooks: Action[AnyContent] = Action {
    Ok(Json.parse(s"""[${listSerializer(books)}]"""))
  }


  /*request.body.valdidate[T]
    request has the request type. looks like : POST /api/books
    body is the body of the json being sent. {...}
    validate gets the datatype(class) and then perform def apply based on the class based on the body of the json
    Note: map method of validate returns OPTION. 

    Example : 
      request.body.valdidate[Book].map{
        book => 
          println(book) prints Book(1,"Steph","Stephen")
          // but if invalid format, it will look directly unto the else in the get or else
      }.getOrElse(BadRequest("Invalid book format"))
  */
  def addBook: Action[JsValue] = Action(parse.json) { request =>
    println("Request: "+ request.body.validate[Book])
    request.body.validate[Book].map { book =>
      books = books :+ book
      Created(Json.toJson(book))
    }.getOrElse(BadRequest("Invalid book format"))
  }

  def getBook(id: Long): Action[AnyContent] = Action {
    books.find(_.id == id) match {
      case Some(a) => Ok(Json.parse(s"""${bookSerializer(a)}"""))
      case None => NotFound("Nothing brother")
    }
  }
}
