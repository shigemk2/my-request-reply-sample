package com.example

import akka.actor._

object RequestReplyDriver extends CompletableApp(1) {
}

case class Request(what: String)
case class Reply(what: String)
case class StartWith(server: ActorRef)

class Client extends Actor {
  def receive = {
    case _ =>
      println("Client: received unexpected message")
  }
}


