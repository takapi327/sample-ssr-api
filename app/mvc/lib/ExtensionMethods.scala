package mvc.lib

import play.api.mvc._

import scala.concurrent.Future

import cats.data.EitherT
import cats.instances.future._

trait ExtensionMethods {
  self: BaseController =>

  implicit lazy val parser = parse.default

  // ----[ Alias ]-------------------------------
  val JsonHelper = mvc.util.JsonHelper

  /** The ExecutionContext with using on Play Framework */
  implicit lazy val executionContext = defaultExecutionContext

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
