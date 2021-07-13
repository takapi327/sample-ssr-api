package domain.model.article

import domain.value.Article

case class SpecialArticle(
  microCMSContentId: String,
  title:             String,
  body:              String
) extends Article

object SpecialArticle {}
