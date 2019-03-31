package art.tp.gdpr.dao

import org.apache.spark.sql.SQLContext

class JsonReader(ctx: SQLContext, jsonFile:String) extends Reader{

  override def extractAction(ctx: SQLContext, file: String): Unit = ???

  def getDataFrame() ={
    ctx.read.json(jsonFile)
  }
}