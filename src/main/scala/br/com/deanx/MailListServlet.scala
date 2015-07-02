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
    val email = {params("email")}

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
      <form method="post" action="http://ec2-52-26-222-71.us-west-2.compute.amazonaws.com:8080/vote">
        <h4>How often you think about to kill your developer?</h4>
        <input type="radio" name="dev" value="ed">> Every day<br/>
        <input type="radio" name="dev" value="eh">> Every hour<br/>
        <input type="radio" name="dev" value="ad">> Already done!<br/>
        <input type="submit" value="send"/>
      </form>
      </form>
      </div>""",
      text = "",
      subject = "A question from our API",
      from_email = "alex@deanx.com.br",
      from_name = "Alex Costa",
      to = List(MTo(email)),
      bcc_address = "",
      tracking_domain = "scala-mandrill",
      signing_domain = "scalatra-sign",
      return_path_domain = "Alex Costa",
      tags = List("scalatra")
    )

    MandrillAsyncClient.messagesSend(MSendMessage(message=msg, key="r0oO5JSClGGoB4XmPNTeJA"))
  }
}
