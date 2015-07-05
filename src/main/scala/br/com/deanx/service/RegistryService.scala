package br.com.deanx.service

import java.util.Calendar

import com.joypeg.scamandrill.client.MandrillAsyncClient
import com.joypeg.scamandrill.models.MSendMessage
import com.joypeg.scamandrill.models.MTo
import com.joypeg.scamandrill.models._

import br.com.deanx.models.Registry

object RegistryService {
  def getMailHash(registry:Registry):String = {
    val timestamp: Long = System.currentTimeMillis / 1000

    val md = java.security.MessageDigest.getInstance("SHA-1")
    md.digest((registry.email.concat(timestamp.toString)).getBytes("UTF-8")).map("%02x".format(_)).mkString

  }

  def sendMailTo(registry:Registry) = {
    val token = RegistryService.getMailHash(registry)
    val msg = new MSendMsg(
      html = """
      <div style="border:1px solid black;border-radius:4px;padding:10px">
        <h4>How often you think about to kill your developer?</h4>
        <p><a href="http://52.24.71.1:8080/vote?vote=ed&key=token"> Every day</a></p>
        <p><a href="http://52.24.71.1:8080/vote?vote=eh&key=token"> Every hour</a></p>
        <p><a href="http://52.24.71.1:8080/vote?vote=ad&key=token"> Already done!</a></p>
      </div>""".replaceAll("token", token),
      text = "",
      subject = "A question from our API",
      from_email = "alex@deanx.com.br",
      from_name = "Alex Costa",
      to = List(MTo(registry.email)),
      bcc_address = "",
      tracking_domain = "scala-mandrill",
      signing_domain = "scalatra-sign",
      return_path_domain = "Alex Costa",
      tags = List("scalatra")
    )

    MandrillAsyncClient.messagesSend(MSendMessage(message=msg, key="r0oO5JSClGGoB4XmPNTeJA"))

  }
}
