package com.example

import akka.actor._

object RequestReplyDriver extends CompletableApp(1) {
  val client = system.actorOf(Props[Client], "client")
  val server = system.actorOf(Props[Server], "server")
  client ! StartWith(server)

  awaitCompletion
  println("RequestReply: is completed.")
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

class Server extends Actor {
  def receive = {
    case Request(what) =>
      println("Server: received request value: " + what)
      sender ! Reply("RESP-1 for " + what)
    case _ =>
      println("Server received unexpected message")
  }
}

