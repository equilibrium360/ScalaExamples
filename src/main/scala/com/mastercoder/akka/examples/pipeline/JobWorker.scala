package com.mastercoder.akka.examples.pipeline

import akka.actor.Actor

/**
  * Created by UNIVERSE on 2/10/18.
  */
class JobWorker extends Actor{

  def receive = {
    case ExecuteJob(jb:JobObject) => {
      println("Started executing Job" + jb )
      println("Done executing Job"  )
    }
  }

}
