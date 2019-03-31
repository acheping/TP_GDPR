package art.tp.gdpr.controller

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object Context {
  val THREAD_NNUMBER = 2

  def getSQLContext(appName:String): SQLContext ={
    val conf = new SparkConf()
      .setAppName(appName)
      .setMaster("local[" + THREAD_NNUMBER + "]")

    val sc = new SparkContext(conf)
    new SQLContext(sc)
  }

  def getHiveContext(): Unit ={
    ???
  }
}
