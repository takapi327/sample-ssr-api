package mvc.lib.backend

trait BasicRepository extends SlickDatabaseConfig {
  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
}
