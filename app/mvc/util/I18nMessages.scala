package mvc.util

case class I18nMessages(
  uri:      String,
  messages: play.api.i18n.Messages,
  request:  play.api.mvc.RequestHeader
) {

  lazy val confPath = uri.replaceAll("/", ".").replaceFirst("""^.""", "")

  def apply(message: String, args: Any*): String = {
    messages(traversePath(_ + message), args)
  }

  protected def traversePath(block: String => String): Seq[String] = {
    confPath.split('.').inits.toSeq.map(
      parts => block(parts.length match {
        case 0 => parts.mkString(".")
        case _ => parts.mkString(".") + "."
      })
    )
  }
}