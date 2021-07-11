package mvc.lib.backend

import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.Future

trait SlickDatabaseConfig {

  private lazy val masterDB: Database = Database.forConfig("slick.dbs.master")
  private lazy val slaveDB:  Database = Database.forConfig("slick.dbs.slave")

  def slickDBMasterAction[R](action: DBIOAction[R, NoStream, Nothing]): Future[R] = masterDB.run(action)
  def slickDBSlaveAction[R](action: DBIOAction[R, NoStream, Nothing]):  Future[R] = slaveDB.run(action)
}

object SlickDatabaseConfig extends SlickDatabaseConfig
