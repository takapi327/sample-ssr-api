package mvc.lib.backend

import slick.jdbc.JdbcProfile

trait BasicTable[P <: JdbcProfile] extends ProfileComponent[P] {

  import profile.api._

  abstract class SlickTable[M](
    tag:       Tag,
    tableName: String
  ) extends Table[M](tag, tableName)
}
