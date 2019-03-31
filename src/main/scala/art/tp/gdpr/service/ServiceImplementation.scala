package art.tp.gdpr.service

import org.apache.spark.sql
import org.apache.spark

/*
class ServiceImplementation(actionsDf:sql.DataFrame) extends Service{
  val listActionsDelete:sql.DataFrame = setActions("delete")
  val listActionsAnonymize:sql.DataFrame = setActions("anonymization")

  def setActions(action:String) ={
    val stmt = "action = '" + action + "'"
    DataFrame df = actionsDf.filter(stmt)
    df
  }

  def actionFullAnonymize()={
    listActionsDelete.collect()
  }

  def actionFullQuarantine()={
    listActionsDelete.collect()
  }



}
*/