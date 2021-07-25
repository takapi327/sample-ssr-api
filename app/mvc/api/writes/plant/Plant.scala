package mvc.api.writes.plant

import play.api.libs.json.Json

import domain.model.plant.Plant

case class JsValuePlant(
  id:    Long,
  name:  String,
  price: Int
)

object JsValuePlant {
  implicit val writes = Json.writes[JsValuePlant]

  def build(plant: Plant): JsValuePlant = {
    JsValuePlant(
      id    = plant.id.getOrElse(0),
      name  = plant.name,
      price = plant.price.toInt
    )
  }
}
