package com.mastercoder.akka.examples.pipeline

import akka.actor.{Props, Actor}
import com.typesafe.config.ConfigFactory
import collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
  * Created by UNIVERSE on 2/9/18.
  */
class PipelineSupervisor extends Actor{

  val pipelineFlow = ConfigFactory.load().getConfig("pipelineFlow")
  val jobConfigList = pipelineFlow.getConfigList("jobSeq").asScala

  val dependencyJobs = currentJobsLeft.filter(_.dependancyJobs.size !=0)
  val doneJobs = ArrayBuffer[JobObject]()
  val zeroDependencyJobsToStart = currentJobsLeft.filter(_.dependancyJobs.size == 0 )
  val allJobsBuffer = jobConfigList.map(jc => new JobObject(jc))

  val allJobs:ArrayBuffer[JobObject] = ArrayBuffer.empty[JobObject] ++= allJobsBuffer

  val currentJobsLeft:ArrayBuffer[JobObject] = ArrayBuffer.empty[JobObject] ++= allJobsBuffer

  var allJobsCount = allJobs.size
  var completedJobsCount = 0




  println(doneJobs.size)

  //update currentJobsLeft
  //get new list of  dependency jobs
  //get new list of  zero Dependency Jobs

  // Start Executing zero Dependency Jobs
  //reset Zero Dependency jobs

/*  def updateDependencyJobs(compJob: JobObject):Unit = {

    dependencyJobs.map(j => {
        j.dependancyJobs-=compJob.uniqueId
    })

  }*/
 def resetZeroDependencyJobs():Unit = {
   zeroDependencyJobsToStart.remove(0,zeroDependencyJobsToStart.size)
}

  def startZeroDependencyJobs():Unit = {
    zeroDependencyJobsToStart.foreach(jb => {
      val actorRef = context.actorOf(Props[JobWorker], name=jb.uniqueId)
      actorRef ! ExecuteJob(jb)
    })
  }



  def receive = {

    case ExecutePipeline => {

      startZeroDependencyJobs()
      resetZeroDependencyJobs()

    }
    case JobCompleted(comJb: JobObject) => {

      doneJobs+=comJb

    }
    case _ => println("aaaaaaaa")
  }


}
