package mvc.api.reads.article

import play.api.libs.json._

case class JsValueMicroCMSArticle(
  microCMSContentId: String,
  title:             String,
  body:              String
)

object SpecialArticle {
  implicit val reads = Json.reads[JsValueMicroCMSArticle]
}