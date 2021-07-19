package domain.model.article

import domain.value.Article
import play.api.libs.json._

case class SpecialArticle(
  microCMSContentId: String,
  title:             String,
  body:              String
) extends Article

object SpecialArticle {
  implicit val reads = Json.reads[SpecialArticle]
  implicit val writes = Json.writes[SpecialArticle]
}
