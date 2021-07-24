package interfaceAdapter.gateway.repository.plant

object packageDB {
  implicit lazy val profile = slick.jdbc.MySQLProfile
  object PlantRepository extends PlantRepository
}
