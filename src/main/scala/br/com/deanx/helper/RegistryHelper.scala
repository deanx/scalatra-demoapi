package br.com.deanx.helper

object RegistryHelper {
  def voteFromAbbreviationToReadable(abbr:String):String={
    abbr match {
      case "ed" => "Every day"
      case "eh" => "Every hour"
      case "ad" => "Already done!"
    }
  }
}
