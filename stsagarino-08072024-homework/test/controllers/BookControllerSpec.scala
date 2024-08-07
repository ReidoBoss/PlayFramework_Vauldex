package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import java.util.UUID

class BookControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "BookController GET" should {

    "search books" in {
      val request = FakeRequest(GET, "/api/books/search?title=Scala").withHeaders("Accept" -> "application/json")
      val result = route(app, request).get

      status(result) mustBe OK
      contentAsString(result) must include("Search books by title or author")
    }

    "list all available books" in {
      val request = FakeRequest(GET, "/api/books").withHeaders("Accept" -> "application/json")
      val result = route(app, request).get

      status(result) mustBe OK
      contentAsString(result) must include("List of all available books")
    }

    "borrow a book" in {
      val bookId = UUID.randomUUID()
      val request = FakeRequest(POST, s"/api/books/borrow/$bookId").withHeaders("Accept" -> "application/json")
      val result = route(app, request).get

      status(result) mustBe OK
      contentAsString(result) must include(s"Borrow book with ID: $bookId")
    }

    "return a book" in {
      val bookId = UUID.randomUUID()
      val request = FakeRequest(POST, s"/api/books/return/$bookId").withHeaders("Accept" -> "application/json")
      val result = route(app, request).get

      status(result) mustBe OK
      contentAsString(result) must include(s"Return book with ID: $bookId")
    }
  }
}
