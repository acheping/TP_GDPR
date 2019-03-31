package art.tp.gdpr.domain

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait DelFileJsonModel extends DefaultJsonProtocol {
  implicit val csvRequest: RootJsonFormat[CsvJsonModel] = jsonFormat3(CsvJsonModel)
}
