package art.tp.gdpr.service

import art.tp.gdpr.controller.GdprBatch.DATA_PATH
import art.tp.gdpr.dao.{JsonActionReader, LakeWriter}
import org.apache.spark.sql.SparkSession
import art.tp.gdpr.domain._
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

case class ServiceImplementation(dataPath: String, quarantinePath: String) {
  implicit val sparkSession: SparkSession =
    SparkSession.builder().master("local[2]").getOrCreate()

  def getActionFile(file: String) = {

    val actionJson = new JsonActionReader().extractActionFile(file)

    actionJson
  }

  def getActionRow(file:String)= {
    val actionJson = new JsonActionReader().extractActionRow(file)

    actionJson
  }


  def fileQuarantine(fileAction: FileJsonModel) = {
    val lakeWriter = new LakeWriter()
    val srcPath = dataPath + "\\" + fileAction.id
    val destPath = quarantinePath //+ "\\" + fileAction.id

    //retrieve files
    val csvFiles = FileSystem.get(new Configuration()).listStatus(new Path(srcPath))

    //loop to check types of files
    csvFiles.foreach(elt => {
      println(elt.toString)
      if (elt.isFile) {
        println(elt.getPath.toString)
        println(destPath + "\\" +  elt.getPath.getName)
        //move file
        lakeWriter.quanrantine(srcPath + "\\" +  elt.getPath.getName, destPath + "\\" +  elt.getPath.getName)
      }
    })
  }


  def rowQuarantine(rowsAction: RowJsonModel)={
    val nameList: Array[Array[String]] = rowsAction.searchValues

    //get searchCriteria
    val searchCriteria: Array[SearchCriteriaModel] = rowsAction.schema.searchColumns

    case class Person (firstName:String, lastName:String, gender:String)
    //loop on searchValues
    var peopleToDelete:Array[Person] = new Array[Person](nameList.size)
    var i = 0
    nameList.foreach(elt => {
      val firstName = elt(0).toString
      val lastnNme =  elt(1).toString
      val gender =  elt(2).toString

      peopleToDelete(i) = Person(firstName, lastnNme, gender)
      i = i + 1
    })

    //load data file into dataframe

    //loop on searchCriteria and searchValues to apply rules : using of yield ???

    

  }

}
