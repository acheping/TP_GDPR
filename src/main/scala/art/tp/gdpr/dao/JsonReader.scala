package art.tp.gdpr.dao

import art.tp.gdpr.domain._
import spray.json._

import scala.io.Source

class JsonActionReader()extends FileJsonProtocol with RowJsonProtocol {

  def extractActionFile(jsonFile: String)={
    Source.fromFile(jsonFile).getLines.mkString.parseJson.convertTo[FileJsonModel]
  }

  def extractActionRow(jsonFile: String)={
    Source.fromFile(jsonFile).getLines.mkString.parseJson.convertTo[RowJsonModel]
  }


}