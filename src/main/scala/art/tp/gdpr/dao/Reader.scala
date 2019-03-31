package art.tp.gdpr.dao

import org.apache.spark.sql.SQLContext

trait Reader {
  def extractAction(ctx: SQLContext, file:String)
}
