package com.example

import akka.actor._

object RequestReplyDriver extends CompletableApp(1) {
}

class Client extends Actor {
  def receive = {
    case _ =>
      println("Client: received unexpected message")
  }
}


