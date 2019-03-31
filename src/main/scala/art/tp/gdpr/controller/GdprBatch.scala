package art.tp.gdpr.controller

//import art.tp.gdpr.service.ServiceImplementation
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object GdprBatch {
  val APP_NAME = "GDPR compliance"
  val JSON_FILE = "D:\\Homeware\\DEV\\TP_GDPR\\src\\main\\resources\\gdpr_actions.json"
  val PATH = "D:\\Homeware\\DEV\\TP_GDPR\\FileSet\\"

  def main(args: Array[String]): Unit = {

    val sparkSession:SparkSession = SparkSession.builder().master("local[*]").getOrCreate()

    Logger.getLogger("org").setLevel(Level.OFF)

    val dfJSon = sparkSession.read.json(PATH.concat( "person.json"))

    dfJSon.show()

    dfJSon.select("name").show()

    dfJSon.printSchema()

    import sparkSession.implicits._
    dfJSon.filter($"age" > 23).show()

    dfJSon.select(avg( $"age")).show()

    

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
