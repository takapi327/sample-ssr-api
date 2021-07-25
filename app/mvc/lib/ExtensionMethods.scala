package mvc.lib

import play.api.mvc._
import play.api.Logging

import scala.concurrent.Future

import cats.data.EitherT
import cats.instances.future._

trait ExtensionMethods extends Logging {
  self: BaseController =>

  implicit lazy val parser = parse.default

  // ----[ Alias ]-------------------------------
  val JsonHelper = mvc.util.JsonHelper

  /** The ExecutionContext with using on Play Framework */
  implicit lazy val executionContext = defaultExecutionContext

  // --[ Utility Methods for i18n ] -------------------------------------------------------------
  implicit def toI18nMessages(implicit
    messages: play.api.i18n.Messages,
    request:  play.api.mvc.RequestHeader
  ): mvc.util.I18nMessages = mvc.util.I18nMessages(
    uri      = request.uri,
    messages = messages,
    request  = request
  )

  implicit def toI18nMessages(uri: String)(implicit
    messages: play.api.i18n.Messages,
    request:  play.api.mvc.RequestHeader
  ): mvc.util.I18nMessages = mvc.util.I18nMessages(
    uri      = uri,
    messages = messages,
    request  = request
  )

  // --[ Methods ] -------------------------------------------------------------
  // Either[Result, Result] -> Result
  implicit def convertEitherToResult(v: Either[Result, Result]): Result =
    v match { case Right(r) => r case Left(l) => l }

  // Future[Either[Result, Result]] -> Future[Result]
  implicit def convertEitherToResult(f: Future[Either[Result, Result]]): Future[Result] =
    f.map(convertEitherToResult(_))

  // EitherT[Future, Result, Result] -> Future[Result]
  implicit def convertEitherToResult(t: EitherT[Future, Result, Result]): Future[Result] =
    t.valueOr(v => v)
}
