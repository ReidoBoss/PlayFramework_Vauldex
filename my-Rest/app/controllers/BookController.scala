package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

case class Books(var id:Int,title:String,author:String)

object Books:
  implicit val bookSerializer:OFormat[Books] = Json.format[Books]

def bookSerializer(book:Books):String = {
  s"""{"id":${book.id}, "title": "${book.title}", "author": "${book.author}"}"""
}

def listSerializer(list:List[Books])=
  list.map{x=>bookSerializer(x)}.mkString(", ")

//Action[JsValue]
@Singleton
class BookController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  var books = List(Books(1,"StephenGwapo","Stephen Author"))

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def getBooks() = Action{
    Ok(Json.parse(s"""[${listSerializer(books)}]"""))
  }
  def getBook(id:Int) = Action{
    books.find(_.id==id) match {
      case Some(a) => Ok(Json.parse(bookSerializer(a)))
      case None => NotFound("Not Found")
    }
  }
  def addBook():Action[JsValue] = Action(parse.json){
    request => 
      request.body.validate[Books].map{
        book=> 
          book.id = books.last.id+1
          books = books :+ book 
          Ok(Json.parse(bookSerializer(book)))
      }.getOrElse(NotAcceptable("Invalid format"))
  }


}




