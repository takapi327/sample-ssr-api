package interfaceAdapter.gateway.repository.plant

object onMySQL {
  implicit lazy val profile = slick.jdbc.MySQLProfile
  object PlantRepository extends PlantRepository
}
