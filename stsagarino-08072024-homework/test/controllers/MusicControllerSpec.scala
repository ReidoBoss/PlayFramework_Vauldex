package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class MusicControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "MusicController GET" should {

    "list all available songs" in {
      val request = FakeRequest(GET, "/api/songs")
      val result = route(app, request).get

      status(result) mustBe OK
      contentAsString(result) must include("List of all available songs")
    }

    "search songs" in {
      val request = FakeRequest(GET, "/api/songs/search?title=Hello")
      val result = route(app, request).get

      status(result) mustBe OK
      contentAsString(result) must include("Search songs by title, artist, or album")
    }

    "create a new playlist" in {
      val request = FakeRequest(POST, "/api/playlists")
      val result = route(app, request).get

      status(result) mustBe OK
      contentAsString(result) must include("Create a new playlist")
    }

    "add a song to a playlist" in {
      val playlistId = UUID.randomUUID()
      val songId = UUID.randomUUID()
      val request = FakeRequest(POST, s"/api/playlists/$playlistId/songs?songId=$songId")
      val result = route(app, request).get

      status(result) mustBe OK
      contentAsString(result) must include(s"Add song with ID: $songId to playlist with ID: $playlistId")
    }

    "remove a song from a playlist" in {
      val playlistId = UUID.randomUUID()
      val songId = UUID.randomUUID()
      val request = FakeRequest(DELETE, s"/api/playlists/$playlistId/songs/$songId")
      val result = route(app, request).get

      status(result) mustBe OK
      contentAsString(result) must include(s"Remove song with ID: $songId from playlist with ID: $playlistId")
    }

    "play a song" in {
      val songId = UUID.randomUUID()
      val request = FakeRequest(POST, s"/api/songs/play/$songId")
      val result = route(app, request).get

      status(result) mustBe OK
      contentAsString(result) must include(s"Play song with ID: $songId")
    }
  }
}
