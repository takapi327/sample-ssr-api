package domain.model.plant

import Plant.Category._
case class Plant(
  name:     String,
  category: Category
)

object Plant {

  object Category extends Enumeration {
    type Category = Value
    val HousePlant: Category = Value
  }
}
