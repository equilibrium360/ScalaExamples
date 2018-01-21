package com.mastercoder.akka.examples

import akka.actor.{Props, ActorSystem, Actor}
import akka.event.Logging
import akka.routing.RoundRobinPool

/**
  * Created by MASTER on 1/20/2018.
  */
object SupervisorActorExample extends App{

  val actorSys = ActorSystem("ActorSystem")
  val workerActor = actorSys.actorOf(Props[ExportTablesWorker], name="worker")

  val tm = Item(0)
  workerActor ! ProcessItem(tm)

//  workerActor ! tm

  ////////////////////

  val supervisorActor = actorSys.actorOf(Props[ExportSupervisor], name="exportsupervisor")

  supervisorActor ! StartProcessingItems



}

case class Item(id:Int)
case class ProcessItem(item: Item)
case object StartProcessingItems

class ExportSupervisor extends Actor {
  val log = Logging(context.system, "application")

  val workers = context.actorOf(Props[ExportTablesWorker].withRouter(RoundRobinPool(100)))

  def receive = {
    case StartProcessingItems => {

      val allItems  = getItems()

       allItems foreach {itm => workers ! itm}
    }
    case _ => println("NONE ")

  }

  def getItems():List[Item] = {
    Seq(Item(1), Item(2), Item(3), Item(4)).toList
  }
}
class ExportTablesWorker extends Actor {

  def receive = {

    case ProcessItem(item) => println("Received process Item")
    case tmm:Item => println("JUST ITEM "+ tmm.id)
    case _ => println("received none")
  }
}