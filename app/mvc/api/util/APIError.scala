package mvc.api.util

import play.api.libs.json._

case class JsValueAPIError(
  message: String,
  code:    Option[Int]     = None,
  detail:  Option[JsValue] = None
)

object JsValueAPIError {
  implicit val writes = Json.writes[JsValueAPIError]
}
