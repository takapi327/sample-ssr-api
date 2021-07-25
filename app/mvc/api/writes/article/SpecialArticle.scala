package mvc.api.writes.article

import play.api.libs.json._

import mvc.api.writes.plant.JsValuePlant

import domain.model.plant.Plant

case class JsValueSpecialArticle(
  microCMSContentId: String,
  title:             String,
  body:              String,
  plant:             JsValuePlant
)

object SpecialArticle {
  implicit val writes = Json.writes[JsValueSpecialArticle]

  def build(
    id:    String,
    title: String,
    body:  String,
    plant: Plant
  ): JsValueSpecialArticle = {
    JsValueSpecialArticle(
      microCMSContentId = id,
      title             = title,
      body              = body,
      plant             = JsValuePlant.build(plant)
    )
  }
}
