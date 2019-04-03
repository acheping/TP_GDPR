package art.tp.gdpr.controller

import art.tp.gdpr.service.ServiceImplementation
import org.apache.hadoop.conf.Configuration
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions.broadcast
import org.apache.spark.sql.types._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.hadoop.fs.{FileSystem, Path}

object GdprBatch {

  val APP_NAME = "GDPR compliance"
  val HOME_PATH = "D:\\Homeware\\DEV\\TP_GDPR\\FileSet\\"
  val JSON_ACTION_FILE = HOME_PATH + "\\action\\action_delete_file.json"
  val JSON_ACTION_ROWS = HOME_PATH + "\\action\\action_delete_rows.json"
  val DATA_PATH = HOME_PATH + "\\" + "Data"
  val QUARANTINE_PATH = HOME_PATH + "\\" + "Quarantine"

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF)

    val sparkSession = SparkSession
      .builder()
      .master("local")
      .appName(APP_NAME)
      .getOrCreate()

    val service = new ServiceImplementation(DATA_PATH, QUARANTINE_PATH)
    val fileContent = service.getActionFile (JSON_ACTION_FILE)
    val rowContent = service.getActionRow (JSON_ACTION_ROWS)

    //service.fileQuarantine(fileContent)
    service.rowQuarantine(rowContent)



    /*
        val dfJSon = sparkSession.read.json(PATH.concat( "person.json"))

        dfJSon.show()

        dfJSon.select("name").show()

        dfJSon.printSchema()

        import sparkSession.implicits._
        dfJSon.filter($"age" > 23).show()

        dfJSon.select(avg( $"age")).show()

    */


    /*
        Logger.getLogger("org").setLevel(Level.ERROR)

        val sqlContext = Context.getSQLContext(APP_NAME);

        val df = new JsonFile(sqlContext, JSON_FILE).getDataFrame()

        val run:ServiceImplementation = new ServiceImplementation(df);
        run.listActionsDelete.show()
        run.listActionsAnonymize.show()
    */
/*
    df.registerTempTable("JSONdata")

    val data=sqlContext.sql("select * from JSONdata")

    data.show()

    val name: Array[Row] = df.select(df("nom")).collect()
    println(name)
    */
  }
}
