package mvc.util

import play.api.libs.json._
import play.api.mvc.Results._
import play.api.mvc._
import play.api.Logging

import mvc.api.util.JsValueAPIError

object JsonHelper extends Logging{

  def bindFromRequest[T](implicit request: Request[AnyContent], reads: Reads[T]): Either[Result, T] = {
    request.body.asJson match {
      case Some(json) => json.validate[T] match {
        case JsSuccess(v, _) => Right(v)
        case JsError(error)  => Left {
          logger.error(JsError.toJson(error).toString())
          BadRequest(Json.toJson(
            JsValueAPIError(
              message = "Illegal value was detected in the transmitted data",
              detail  = Some(JsError.toJson(error))
            )
          ))
        }
      }
      case None => Left {
        BadRequest(Json.toJson(
          JsValueAPIError(
            message = "Illegal value was detected in the transmitted data"
          )
        ))
      }
    }
  }
}
