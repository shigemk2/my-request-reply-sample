package com.example

import akka.actor._

object RequestReplyDriver extends CompletableApp(1) {
}

case class Request(what: String)
case class Reply(what: String)
case class StartWith(server: ActorRef)

class Client extends Actor {
  def receive = {
    case StartWith(server) =>
      println("Client: is starting....")
      server ! Request("REQ-1")
    case Reply(what) =>
      println("Client: received response: " + what)
      RequestReplyDriver.completedStep()
    case _ =>
      println("Client: received unexpected message")
  }
}


