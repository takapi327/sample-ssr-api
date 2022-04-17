package mvc.util

import play.api.mvc.{ PathBindable, QueryStringBindable }

case class User(id: Int, name: String)

object Binder {
  class UserBinder(implicit intBinder: PathBindable[Int], stringBinder: QueryStringBindable[String], intQueryBinder: QueryStringBindable[Int])
    extends PathBindable[User] with QueryStringBindable[User] {

    override def bindPath(key: String, value: String): Either[String, User] = {
      for {
        id   <- intBinder.bindPath(key, value)
        user <- Right(User(id, "PathBindable"))
      } yield user
    }

    override def bindQuery(key: String, params: Map[String, Seq[String]]): Option[Either[String, User]] = {
      for {
        id   <- intQueryBinder.bindQuery("id", params)
        name <- stringBinder.bindQuery("name", params)
      } yield {
        (id, name) match {
          case (Right(id), Right(name)) => Right(User(id, name))
          case _                        => Left("Unable to bind an User")
        }
      }
    }
    override def unbindPath(key: String, user: User): String =
      user.id.toString

    override def unbindQuery(key: String, user: User): String =
      intQueryBinder.unbindQuery("id", user.id) + "&" + stringBinder.unbindQuery("name", user.name)
  }

  implicit def pathBinder(implicit intBinder: PathBindable[Int]): PathBindable[User] = new UserBinder

  implicit def queryStringBindable(implicit intBinder: QueryStringBindable[Int], stringBinder: QueryStringBindable[String]) = new UserBinder
}
