package br.com.deanx

import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class MailListServletSpec extends ScalatraSpec {
	addServlet(classOf[MailListServlet], "/*")

	def is = s2"""
  GET / on MailListServlet
    returns status 200           $getRoot200

  POST / on MailListServlet
  	returns status 200			$postRoot200
"""




  def getRoot200 = get("/") {
    status must_== 200
  }

  def postRoot200 = post("/") {
  	status must_==200
  }
}
