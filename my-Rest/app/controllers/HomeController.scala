package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

//Action[JsValue]
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {


  def index: Action[JsValue] = Action (parse.json){ request =>
    val meow = (request.body \ "name" ).as[String]
    Ok("meow" + "my name is jegg" + s"$meow") 
  }

}




