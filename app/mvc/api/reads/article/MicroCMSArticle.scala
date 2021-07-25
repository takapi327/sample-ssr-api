package mvc.api.reads.article

import play.api.libs.json._

case class JsValueMicroCMSArticle(
  id:      String,
  title:   String,
  body:    String,
  plantId: String
)

object JsValueMicroCMSArticle {
  implicit val reads = Json.reads[JsValueMicroCMSArticle]
}

case class JsValueMicroCMSArticleContents(
  contents: Seq[JsValueMicroCMSArticle]
)

object JsValueMicroCMSArticleContents {
  implicit val reads = Json.reads[JsValueMicroCMSArticleContents]
}
