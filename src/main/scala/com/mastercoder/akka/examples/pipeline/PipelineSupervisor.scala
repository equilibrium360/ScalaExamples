package com.mastercoder.akka.examples.pipeline

import akka.actor.{Props, Actor}
import com.typesafe.config.ConfigFactory
import collection.JavaConverters._
import scala.collection.immutable.List

/**
  * Created by UNIVERSE on 2/9/18.
  */
class PipelineSupervisor extends Actor{

  val pipelineFlow = ConfigFactory.load().getConfig("pipelineFlow")
  val jobConfigList = pipelineFlow.getConfigList("jobSeq").asScala

  var dependencyJobs = currentJobsLeft.filter(_.dependancyJobs.size !=0)
  var doneJobs = List.empty[JobObject]
  var zeroDependencyJobsToStart = currentJobsLeft.filter(_.dependancyJobs.size == 0 )
  val allJobsBuffer = jobConfigList.map(jc => new JobObject(jc))

  val allJobs:List[JobObject] = List.empty[JobObject] ++ allJobsBuffer

  var currentJobsLeft:List[JobObject] = List.empty[JobObject] ++ allJobsBuffer

  var allJobsCount = allJobs.size
  var completedJobsCount = 0




  println(doneJobs.size)


  def updateCurrentJobsLeft(compJob: JobObject):Unit = {

    currentJobsLeft = dependencyJobs.map(j => {
      j.dependancyJobs-=compJob.uniqueId
      j
    })

  }

  def areAllJobsDone():Boolean = {

    completedJobsCount == allJobsCount

  }
  def updateDoneJobs(cj:JobObject):Unit = {
    doneJobs =   doneJobs  :+ cj
    completedJobsCount = completedJobsCount +1
  }

  def updateDependencyJobsList() = {

    dependencyJobs = currentJobsLeft.filter(_.dependancyJobs.size !=0)

  }

  def updateZeroDependencyJobsList() = {

    zeroDependencyJobsToStart = currentJobsLeft.filter(_.dependancyJobs.size == 0 )

  }



 def resetZeroDependencyJobs():Unit = {
   zeroDependencyJobsToStart = List.empty[JobObject]
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

      //update currentJobsLeft
      //get new list of  dependency jobs
      //get new list of  zero Dependency Jobs

      // Start Executing zero Dependency Jobs
      //reset Zero Dependency jobs

      updateDoneJobs(comJb)
      updateCurrentJobsLeft(comJb)
      updateDependencyJobsList
      updateZeroDependencyJobsList


      //if allJobsDone

      if(areAllJobsDone()) {
        //shutdown System
      }
      startZeroDependencyJobs()
      resetZeroDependencyJobs()






    }
    case _ => println("aaaaaaaa")
  }


}
