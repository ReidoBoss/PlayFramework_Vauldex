file:///C:/Users/PC/Desktop/PlayFramework_Vauldex/REST%20API/app/controllers/BookController.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.3\scala3-library_3-3.3.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 688
uri: file:///C:/Users/PC/Desktop/PlayFramework_Vauldex/REST%20API/app/controllers/BookController.scala
text:
```scala
package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

case class Book(id: Long, title: String, author: String)

object Book {
  implicit val bookFormat: OFormat[Book] = Json.format[Book]
}

@Singleton
class BookController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

    private var books = List(
        Book(1, "1984", "George Orwell"),
        Book(2, "Brave New World", "Aldous Huxley")
    )

    def getBooks: Action[AnyContent] = Action {
        Ok(Json.toJson(books))
    }
    def addBook: Action[JsValue] = Action(parse.json) { request =>
        println(@@)
        request.body.validate[Book].map { book =>
            books = books :+ book
            Created(Json.toJson(book))
        }.getOrElse(BadRequest("Invalid book format"))
    }

}

```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:131)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:186)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:94)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:63)
	scala.meta.internal.pc.MetalsSignatures$.signatures(MetalsSignatures.scala:17)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:51)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:435)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 0