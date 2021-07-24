package domain.model.plant

case class Plant(
  id:    Option[Long],
  name:  String,
  price: BigDecimal
)

object Plant
