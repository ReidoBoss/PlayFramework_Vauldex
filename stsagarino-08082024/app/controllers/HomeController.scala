package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.Future
import java.net.InetAddress
import scala.util.{Try,Failure,Success}
import scala.concurrent.Await
import scala.concurrent.duration.Duration
case class User(id: Int, name: String)

def findUserById(id: Int): Future[Option[User]] = {
  val users = List(
    User(1, "John"),
    User(2, "William"),
    User(3, "James"),
    User(4, "Michael"),
    User(5, "Robert"),
    User(6, "David"),
    User(7, "Thomas"),
    User(8, "Charles"),
    User(9, "George"),
    User(10, "Joseph"))
  Future.successful(users.find(_.id == id))
}

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

// 1. Implement an action that responds a text of "Hello World" with a content type of "text/plain."
  def helloWorld() = Action { implicit request: Request[AnyContent] =>
    Ok("Hello World").as("text/plain")
  }
// 2. Implement an action that responds an HTML of <html></html> with the content type set to "text/xml" or "application/xml." You may not use Twirl.
  def htmlxml() = Action { implicit request: Request[AnyContent] =>
    Ok(<html> </html>).as("text/xml")
  }
// 3. Implement an action that responds an HTML of <html></html> with the content type set to "text/html." You may not use Twirl.
  def html() = Action { implicit request: Request[AnyContent] =>
    Ok(<html></html>).as("text/html")
  }
// 4. Implement an action that responds an HTML of <html><head><title> Hello World</title></head><body><h1>Hello World</h1></body></html> with the content type set to "text/html" using Twirl.
  def htmlHello = Action { implicit request: Request[AnyContent] =>
    Ok(<html><head><title> Hello World</title></head><body><h1>Hello World</h1></body></html> ).as("text/html")
  }
// 5. Implement an action that responds a text of the client IP address from the request.
  def address = Action { implicit request: Request[AnyContent] =>
    Ok(InetAddress.getLocalHost().toString)
  }
// 6. Implement an action that handles POST request containing a JSON value and responds the exact JSON value received. If there was no json body received, a text "No JSON body" will be returned instead.
  def getJson():Action[JsValue] = Action(parse.json){
    request => 
      if(request.body.toString.length!=2)
        Ok(Json.parse("""{"Status":"201"}"""))
      else 
        Ok("No JSON body")   
  }

  // def findUser(id:Int) = Action { implicit request: Request[AnyContent] =>
  //   val userid = findUserById(id)
  //   val duration:scala.concurrent.duration.Duration   = Duration("1000")
  //   println(Await.result(userid,duration))
  //   Ok("meow")
  // }
  


}
