package mvc.util

import play.api.libs.json._
import play.api.mvc.Results._
import play.api.mvc._

object JsonHelper {

  def bindFromRequest[T](implicit request: Request[AnyContent], reads: Reads[T]): Either[Result, T] = {
    request.body.asJson match {
      case Some(json) => json.validate[T] match {
        case JsSuccess(v, _) => Right(v)
        case JsError(error)  => Left(BadRequest)
      }
      case None => Left(BadRequest)
    }
  }
}
