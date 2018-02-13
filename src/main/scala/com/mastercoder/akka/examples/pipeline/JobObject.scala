package com.mastercoder.akka.examples.pipeline

import com.typesafe.config.Config

import scala.collection.immutable.Set

import collection.JavaConverters._

/**
  * Created by UNIVERSE on 2/9/18.
  */
class JobObject(val uniqueId:String, val name:String,val dependsOn:Array[String], var dependancyJobs:Set[String], var jobDone:Boolean = false ) {


  def this(jc: Config) {



    this(jc.getString("uniqueId"),jc.getString("name"), jc.getStringList("dependsOn"),collection.immutable.Set(jc.getStringList("dependsOn").asScala.filterNot(_.equals("none"))), false )

  }


}
