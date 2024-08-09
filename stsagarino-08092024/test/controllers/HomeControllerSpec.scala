package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "Create Session Action" should {
    "return its appropriate success result" in {
      val controller = new HomeController(stubControllerComponents())
      val session = controller.createSession
      status(session) mustBe OK
    }
  }
  
  "Home Action" should {
    "return its appropriate success result" in {
      val controller = new HomeController(stubControllerComponents())
      val session = controller.home
      status(session) mustBe OK
    }
  }
}