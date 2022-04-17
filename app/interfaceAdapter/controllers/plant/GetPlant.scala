package interfaceAdapter.controllers.plant

import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json

import mvc.lib.ExtensionMethods
import mvc.api.writes.plant.JsValuePlant
import interfaceAdapter.gateway.repository.plant.onMySQL.PlantRepository

class GetPlantController @Inject()(implicit
  cc: MessagesControllerComponents
) extends AbstractController(cc)
     with ExtensionMethods {

  def getById(id: Long) = Action async {
    for {
      Some(plant) <- PlantRepository.getById(id)
    } yield {
      Ok(Json.toJson(
        JsValuePlant.build(plant)
      ))
    }
  }

  def user(user: mvc.util.User) = Action {
    Ok(Json.obj("id" -> user.id, "name" -> user.name))
  }
}
