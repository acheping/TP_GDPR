package art.tp.gdpr.dao

import java.io.File
import java.nio.file.{Files, Paths, StandardCopyOption}

class LakeWriter {

  def applyUpdate: Unit = {
    val query = "Msck repair table <db_name>.<table_name>"
  }

  def quanrantine(source: String, target:String) = {
    Files.move(
      Paths.get(source),
      Paths.get(target),
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
