package interfaceAdapter.gateway.repository.plant

import scala.concurrent.Future

import slick.jdbc.JdbcProfile

import mvc.lib.backend.BasicRepository

import interfaceAdapter.gateway.dao.plant.SlickResourceProvider
import domain.model.plant.Plant

case class PlantRepository[P <: JdbcProfile]()(
  implicit val profile: P
) extends BasicRepository
     with SlickResourceProvider[P] {

  import profile.api._

  def getById(id: Long): Future[Option[Plant]] = {
    slickDBSlaveAction {
      plantTable.uniqueId(id).result.headOption
    }
  }
}
