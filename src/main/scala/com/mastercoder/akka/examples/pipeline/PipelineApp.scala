package com.mastercoder.akka.examples.pipeline

import akka.actor.{Props, ActorSystem}

/**
  * Created by UNIVERSE on 2/10/18.
  */
object PipelineApp extends App {


  val actorSys = ActorSystem("PipelineSystem")
  val pipeSuper = actorSys.actorOf(Props[PipelineSupervisor], name="PipelineSupervisor")

  pipeSuper ! ExecutePipeline

}
