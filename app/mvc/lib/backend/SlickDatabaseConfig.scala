package mvc.lib.backend

import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.Future

trait SlickDatabaseConfig {

  private def db(replication: String): Database = Database.forConfig(s"slick.dbs.${replication}.db")

  def slickDBAction[R](replication: String)(action: DBIOAction[R, NoStream, Nothing]): Future[R] = db(replication).run(action)
}

object SlickDatabaseConfig extends SlickDatabaseConfig
