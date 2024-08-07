package controllers

import play.api.mvc._
import java.util.UUID
import javax.inject._

@Singleton
class MusicController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def listSongs = Action { implicit request: Request[AnyContent] =>
    Ok("List of all available songs")
  }

  def search(title: Option[String], artist: Option[String], album: Option[String]) = Action { implicit request: Request[AnyContent] =>
    Ok("Search songs by title, artist, or album")
  }

  def createPlaylist = Action { implicit request: Request[AnyContent] =>
    Ok("Create a new playlist")    // { "name": "Playlist Name" }

  }

  def addSongToPlaylist(id: UUID, songId: UUID) = Action { implicit request: Request[AnyContent] =>
    Ok(s"Add song with ID: $songId to playlist with ID: $id")
  }

  def removeSongFromPlaylist(id: UUID, songId: UUID) = Action { implicit request: Request[AnyContent] =>
    Ok(s"Remove song with ID: $songId from playlist with ID: $id")
  }

  def playSong(id: UUID) = Action { implicit request: Request[AnyContent] =>
    Ok(s"Play song with ID: $id")
  }
}
