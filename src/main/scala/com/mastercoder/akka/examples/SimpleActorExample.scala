package com.mastercoder.akka.examples

import akka.actor.{Props, ActorSystem, Actor}
import akka.event.Logging

/**
  * 1. Pass message to Actor
  * 2. Create Actor whose constructor requires arguments
  */
class HelloActor extends Actor {

  val log = Logging(context.system, this)
  def  receive() = {

    case "hello" => println("Hello Back to you")
    case _ => println("What did you say?")

  }
}

class HelloActorWithArgs(initialArg:String) extends Actor {

  val log = Logging(context.system, this)
  def  receive() = {

    case "hello" => println("Hello Back to you "+initialArg)
    case _ => println("What did you say? "+initialArg)

  }
}
object SimpleActorExample extends App {
    val actorSys = ActorSystem("HelloSystem")
  val helloActor = actorSys.actorOf(Props[HelloActor], name="helloactor")

  helloActor ! "hello"

  val  helloWithArgs = actorSys.actorOf(Props(classOf[HelloActorWithArgs], "0IsInitalValue"))

  helloWithArgs ! "hello"




}
