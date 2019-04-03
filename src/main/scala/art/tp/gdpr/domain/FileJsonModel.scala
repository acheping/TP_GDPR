package art.tp.gdpr.domain

import spray.json._

trait FileJsonProtocol extends DefaultJsonProtocol {
  implicit val csvFileRequest: RootJsonFormat[FileJsonModel] = jsonFormat3(FileJsonModel)
}

case class FileJsonModel(id: Int, `type`: String, path: String) extends JsonModel
