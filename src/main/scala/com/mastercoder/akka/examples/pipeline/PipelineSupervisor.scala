package com.mastercoder.akka.examples.pipeline

import akka.actor.{Props, Actor}
import com.typesafe.config.ConfigFactory
import collection.JavaConverters._

/**
  * Created by UNIVERSE on 2/9/18.
  */
class PipelineSupervisor extends Actor{

  val pipelineFlow = ConfigFactory.load().getConfig("pipelineFlow")

//  pipelineFlow.getList("jobSeq")

  val jobConfigList = pipelineFlow.getConfigList("jobSeq").asScala




  val allJobs = jobConfigList.map(jc => new JobObject(jc))

  val dependancyJobs = allJobs.filter(_.dependancyJobs.size !=0)

  val doneJobs = Vector[JobObject]()

  val zeroDependancyJobs = allJobs.filter(_.dependancyJobs.size == 0 )



  println(doneJobs.size)



  def startZeroDependancyJobs():Unit = {

    zeroDependancyJobs.foreach(jb => {
      val actorRef = context.actorOf(Props[JobWorker], name=jb.uniqueId)
      actorRef ! ExecuteJob(jb)
    })



  }



  def receive = {

    case ExecutePipeline => {

    }
    case _ => println("aaaaaaaa")
  }


}
