package art.tp.gdpr.service

trait Service {
  def setActions(action:String)

  def actionFullAnonymize()

  def actionFullQuarantine()
}
