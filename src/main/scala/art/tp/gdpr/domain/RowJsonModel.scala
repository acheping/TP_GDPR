package art.tp.gdpr.domain

import spray.json._

trait RowJsonProtocol extends DefaultJsonProtocol {
  implicit val csvRowRequest1 = jsonFormat3(SearchCriteriaModel)
  implicit val csvRowRequest2 = jsonFormat8(RowJsonSchemaModel)
  implicit val csvRowRequest3 = jsonFormat4(RowJsonModel)
}

case class SearchCriteriaModel(columnName: String, operator: String, columnType: String)

case class RowJsonSchemaModel(hasHeader: Boolean, charset: String, separator: String, escapeChar: String, quoteChar: String, isMultiline:Boolean, columnCount: Int, searchColumns: Array[SearchCriteriaModel])

case class RowJsonModel(id: Int, `type`: String, schema: RowJsonSchemaModel, searchValues: Array[Array[String]])
