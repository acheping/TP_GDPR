package art.tp.gdpr.dao

import java.io.File
import java.nio.file.{Files, Paths, StandardCopyOption}

class LakeWriter(Table: String) extends Writer {
  val QUARANTINE_PATH = "D:\\Homeware\\DEV\\TP_GDPR\\FileSet\\Quarantine\\"

  override def applyUpdate: Unit = {
    val query = "Msck repair table <db_name>.<table_name>"

  }

  def quanrantine(file: String) = {
    Files.move(
      Paths.get(file),
      Paths.get(file + ".qte"),
      StandardCopyOption.REPLACE_EXISTING
    )
  }

  def delete(file: String): Option[File] = {
    //FileUtils.deleteQuietly(new File(file))
    val f: File = new File(file)
    for {
      foundFile <- new FileMonads(f).check
      deletedFile <- foundFile.remove
    } yield deletedFile
  }

  implicit class FileMonads(f: File) {
    def check = if (f.exists) Some(f) else None //returns "Maybe" monad
    def remove = if (f.delete()) Some(f) else None //returns "Maybe" monad
  }

}
