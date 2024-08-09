package controllers

import scala.collection.mutable.ListBuffer
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

case class User(id: Int, name: String)

var users = ListBuffer(
  User(1, "John"),
  User(2, "William"),
  User(3, "James"),
  User(4, "Michael"),
  User(5, "Robert"),
  User(6, "David"),
  User(7, "Thomas"),
  User(8, "Charles"),
  User(9, "George"),
  User(10, "Joseph")
)



@Singleton
class UserController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

//   1. Implement an action that takes a POST request with a JSON body `{ name: '' }`. This action will create a `User` object and add it to the users list. This action will allow the user to register to your application. The `id` generated will be the index of the newly added User object.
  def addUser():Action[JsValue] = Action(parse.json){
    (request:Request[JsValue]) =>
      val name = (request.body \ "name").as[String]
      users = users :+ User(users.last.id+1,name)
      Ok("User Added!")
  }
// 2. Implement an action that takes a POST request with a JSON body `{ name: ''}`. This action will search a user with the same `name` as received from the request in the `users` list. If the user is found, the action will respond with an `Ok` result together with a session with the value set as the user's id. If the user is not found, the action will return a `NotFound` result. This action will allow the user to login to your application.
  def sessionUser():Action[JsValue] = Action(parse.json){
    (request:Request[JsValue]) =>
      val name = (request.body \ "name").as[String]
      users.find(_.name==name).map{ user=>
        Ok.withSession(request.session + ("id" -> user.id.toString))
      }.getOrElse{
        NotFound
      }
  }
// 3. Implement an action that takes a GET request with a named parameter "user" that is a numerical value. This action only accepts request that have a session with a user's id as value. This action will use the `findUserById` method to find the user. If the user is found, respond with an "Ok" status and the user's string representation obtained by calling toString`. Otherwise,  the action will respond with a "NotFound" status. This action will allow authorized users to find another user from your application.

  def authUser(idUser:Int): Action[AnyContent] = Action { request =>
    request.session
      .get("id")
      .map { id => 
        users.find(_.id==id.toInt&&idUser==id.toInt).map{
          x=>
            Ok(x.toString)
        }.getOrElse(NotFound)
        
      }.getOrElse(Unauthorized(<h1>you are unauthorized</h1>).as(HTML))
  }

// 4. Implement an action that takes a DELETE request. This action will remove the session. This action will allow users to logout from your application.
  def logout() = Action{
    Ok.withNewSession
  }
}
