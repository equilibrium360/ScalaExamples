package com.mastercoder.akka.examples

/**
  * Created by UNIVERSE on 2/10/18.
  */
package object pipeline {

  case object ExecutePipeline
  case class ExecuteJob(jobObject: JobObject)

}
