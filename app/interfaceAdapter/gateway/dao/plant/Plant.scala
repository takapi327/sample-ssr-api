package interfaceAdapter.gateway.dao.plant

import slick.jdbc.JdbcProfile

import domain.model.plant.Plant
import mvc.lib.backend.BasicTable

case class PlantTable[P <: JdbcProfile]()(
  implicit val profile: P
) extends BasicTable[P] {

  import profile.api._

  class Query extends TableQuery(new Table(_)) {
    def uniqueId(id: Long) = this.filter(_.id === id)
  }
  lazy val query = new Query

  class Table(tag: Tag) extends SlickTable[Plant](tag, "plant") {

    def id    = column[Long]       ("id", O.PrimaryKey, O.AutoInc)
    def name  = column[String]     ("name")
    def price = column[BigDecimal] ("price")

    type TableElementTuple = (
      Option[Long], String, String
    )

    def * = (
      id.?, name, price
    ) .<> (
      (Plant.apply _).tupled,
      (Plant.unapply _)
    )
  }
}