package com.mastercoder.akka.examples.pipeline

import akka.actor.Actor

/**
  * Created by UNIVERSE on 2/10/18.
  */
class JobWorker extends Actor{

  def receive = {
    case ExecuteJob(jb:JobObject) => {
      println("Start executing " + jb.uniqueId )
      println("Done executing  "  + jb.uniqueId )

      sender() ! JobCompleted(jb)
    }
  }

}
