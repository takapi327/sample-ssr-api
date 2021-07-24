package interfaceAdapter.gateway.dao.plant

import slick.jdbc.JdbcProfile

trait SlickResourceProvider[P <: JdbcProfile] {

  implicit val profile: P

  object PlantTable extends PlantTable

  lazy val tables = Seq(
    PlantTable
  )
}