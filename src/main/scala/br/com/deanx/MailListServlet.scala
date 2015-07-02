package br.com.deanx

import org.scalatra._
import scalate.ScalateSupport
import dispatch._
import scala.concurrent.ExecutionContext.Implicits.global
import com.joypeg.scamandrill.client.MandrillAsyncClient
import com.joypeg.scamandrill.models.MSendMessage
import com.joypeg.scamandrill.models.MTo
import com.joypeg.scamandrill.models._
import scala.io.Source._

class MailListServlet extends UnearStack {

  get("/") {
    contentType="text/html"
    jade("/home")
  }

  get("/send") {
    {params("email")}

  /*  val svc = dispatch.url("https://mandrillapp.com/api/1.0/send")
    val myPost = svc.POST
    myPost.addParameter("key","r0oO5JSClGGoB4XmPNTeJA")

    val requester = dispatch.Http(svc OK as.String)
    requester.onComplete { x =>

    }
    jade("/home")
    */


    val msg = new MSendMsg(
      html = """
      <div style="border:1px solid black;border-radius:4px;padding:10px">
      <form method="post" action="/">
        <h4>How often you think about to kill your developer?</h4>
        <input type="radio" name="dev" value="ed">> Every day<br/>
        <input type="radio" name="dev" value="eh">> Every hour<br/>
        <input type="radio" name="dev" value="ad">> Already done!<br/>
        <input type="submit" value="send"/>
      </form>
      </form>
      </div>""",
      text = "",
      subject = "subject test",
      from_email = "scamandrill@test.com",
      from_name = "Scamandrill",
      to = List(MTo("alex@deanx.com.br")),
      bcc_address = "",
      tracking_domain = "domain1",
      signing_domain = "domain_sign",
      return_path_domain = "Alex Costa",
      tags = List("exampletag1", "exampletag2")
    )

    MandrillAsyncClient.messagesSend(MSendMessage(message=msg, key="r0oO5JSClGGoB4XmPNTeJA"))
  }
}
