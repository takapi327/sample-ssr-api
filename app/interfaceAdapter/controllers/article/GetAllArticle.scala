package interfaceAdapter.controllers.article

import javax.inject._

import scala.util.{Success, Failure}
import scala.concurrent.Future

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.ws.WSClient
import play.api.Configuration
import play.api.i18n.I18nSupport

import cats.data.EitherT
import cats.implicits._

import mvc.api.writes.article.JsValueSpecialArticle
import mvc.api.reads.article.{ JsValueMicroCMSArticle, JsValueMicroCMSArticleContents }
import mvc.api.util.JsValueAPIError

import interfaceAdapter.gateway.repository.plant.onMySQL.PlantRepository

class GetAllArticleController @Inject()(implicit
 cc:   MessagesControllerComponents,
 ws:   WSClient,
 conf: Configuration
) extends AbstractController(cc)
  with mvc.lib.ExtensionMethods
  with I18nSupport {

  val microCMSURL    = conf.get[String]("microCMS.url")
  val microCMSAPIKey = conf.get[String]("microCMS.apiKey")

  def getAll = Action async {
    apiMicroCMS semiflatMap {
      case json => {
        json match {
          case None    => Future.successful[Result](NotFound)
          case Some(v) => {
            for {
              Some(plant) <- PlantRepository.getById(v.plantId.toLong)
            } yield {
              Ok(Json.toJson(
                JsValueSpecialArticle.build(
                  id    = v.id,
                  title = v.title,
                  body  = v.body,
                  plant = plant
                )
              ))
            }
          }
        }
      }
    }
  }

  def apiMicroCMS: EitherT[Future, Result, Option[JsValueMicroCMSArticle]] = {
    for {
      res <- EitherT {ws.url(microCMSURL + "article").withHttpHeaders("X-API-KEY" -> microCMSAPIKey).get().transform {
               case Success(value)     => Success(Right(value))
               case Failure(exception) => Success(Left {
                 logger.error(Json.toJson(exception.getMessage).toString())
                 BadRequest(Json.toJson(
                   JsValueAPIError(
                     message = "Illegal value was detected in the transmitted data",
                     detail  = Some(Json.toJson(exception.getMessage))
                   )
                 ))
               })
             }}
      json <- EitherT.fromEither[Future] {
                res.json.validate[JsValueMicroCMSArticleContents] match {
                  case JsSuccess(v, _) => Right(v)
                  case JsError(error)  => Left {
                    logger.error(JsError.toJson(error).toString())
                    BadRequest(Json.toJson(
                      JsValueAPIError(
                        message = "Illegal value was detected in the transmitted data",
                        detail = Some(JsError.toJson(error))
                      )
                    ))
                  }
                }
              }
    } yield {
      json.contents.headOption
    }
  }
}