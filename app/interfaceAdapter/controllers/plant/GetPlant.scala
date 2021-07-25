package interfaceAdapter.controllers.plant

import javax.inject._
import play.api.mvc._

import mvc.lib.ExtensionMethods
import interfaceAdapter.gateway.repository.plant.onMySQL.PlantRepository

class GetPlantController @Inject()(implicit
  cc: MessagesControllerComponents
) extends AbstractController(cc)
     with ExtensionMethods {

  def getById(id: Long) = Action async {
    for {
      Some(plant) <- PlantRepository.getById(id)
    } yield {
      Ok(s"ID: ${plant.id}, Name: ${plant.name}, price: ${plant.price.toInt}")
    }
  }
}
