package mvc.api.article

import play.api.libs.json._

case class SpecialArticle(
  microCMSContentId: String,
  title:             String,
  body:              String
)

object SpecialArticle {
  implicit val reads  = Json.reads[SpecialArticle]
  implicit val writes = Json.writes[SpecialArticle]
}
