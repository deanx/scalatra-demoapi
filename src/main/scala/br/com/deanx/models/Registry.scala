package br.com.deanx.models

import com.mongodb.casbah.Imports._
import br.com.deanx.service.RegistryService

case class Registry(name:String ="", email:String="") {
  val hash = RegistryService.getMailHash(this)

  private val mongoClient = MongoClient("localhost", 27017)
  private val db = mongoClient("registry")

  def persist() {

    val coll = db("entries")

    val a = MongoDBObject("name" -> name,"email"->email,"hash"->this.hash,"status"->0)

    coll.insert(a)

  }

  def findByToken(token:String):Registry = {
    val coll = db("entries")

    val finder = MongoDBObject("hash" -> token, "status" -> 0)
    val reg = coll.findOne(finder)

    if(reg.isEmpty) {
      new Registry("","")
    }
    else {
      new Registry(reg.get("name").toString,reg.get("email").toString)
    }
  }

  def vote(vote:String, token:String) {
    val coll = db("entries")
    val finder = MongoDBObject("hash" -> token, "status" -> 0)

    val update = $set("status" -> "1", "vote" -> vote)
    val result = coll.update( finder, update )

  }

  def listAll():MongoCursor = {
    val coll = db("entries")

    coll.find()
  }
}
