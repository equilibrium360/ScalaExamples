package com.mastercoder.examples

import com.typesafe.config.ConfigFactory

/**
  * Created by UNIVERSE on 2/9/18.
  */
object ConfigReadingExample extends App {

  val pipelineFlow = ConfigFactory.load().getConfig("pipelineFlow")

  println(pipelineFlow.getList("jobSeq"))

  println(pipelineFlow.getConfigList("jobSeq"))


}
