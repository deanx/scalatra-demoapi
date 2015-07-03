package br.com.deanx

import org.scalatra._
import scalate.ScalateSupport
import dispatch._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source._
import br.com.deanx.service.RegistryService
import br.com.deanx.models.Registry
class MailListServlet extends UnearStack {

  get("/") {
    contentType="text/html"
    jade("/home")
  }

  get("/send") {
    val email = {params("email")}
    val name = {params("name")}

    val registry = new Registry(name, email)
    registry.persist()
    RegistryService.sendMailTo(registry)
  }

  get("/vote") {
    val token = {params("key")}
    val vote = {params("vote")}

    new Registry().vote(vote,token);

    contentType="text/html"
    jade("/voted")
  }

  get("/list") {
    new Registry().listAll()

    contentType="text/html"
    jade("/list")
  }
}
