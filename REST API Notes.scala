// own way of returning a json 
def bookSerializer(book:Book):String = {
  s"""{"id":${book.id}, "title": "${book.title}", "author": "${book.author}"}"""
}

def listSerializer(arr:List[Book])=
  arr.map{x=>bookSerializer(x)}.mkString(", ")

  // remember to put `[]` if you're returning many bro
  def getBooks: Action[AnyContent] = Action {
    Ok(Json.parse(s"""[${listSerializer(books)}]"""))
  }
// built-in way 
case class Book(id: Long, title: String, author: String)
object Book {
  implicit val bookFormat:OFormat[Book] = Json.format[Book]
}

def getBooks: Action[AnyContent] = Action {
  Ok(Json.toJson(books))
}
