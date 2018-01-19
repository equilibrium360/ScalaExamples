package com.mastercoder.akka.examples

import akka.actor.{Props, ActorSystem, Actor}
import akka.event.Logging

/**
  * Created by MASTER on 1/18/2018.
  */
class HelloActor extends Actor {

  val log = Logging(context.system, this)
  def  receive() = {

    case "hello" => println("Hello Back to you")
    case _ => println("What did you say?")

  }
}
object SimpleActorExample extends App {
    val actorSys = ActorSystem("HelloSystem")
    val helloActor = actorSys.actorOf(Props[HelloActor], name="helloactor")

  helloActor ! "hello"





}
