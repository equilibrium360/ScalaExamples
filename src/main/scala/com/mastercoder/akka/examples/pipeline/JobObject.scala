package com.mastercoder.akka.examples.pipeline

import com.typesafe.config.Config

import scala.collection.immutable.Set

import collection.JavaConverters._

/**
  * Created by UNIVERSE on 2/9/18.
  */
class JobObject(val uniqueId:String,
                val name:String,
                val dependsOn:List[String],
                var dependancyJobs:Set[String],
                var jobDone:Boolean = false ) {


  def this(jc: Config) {

    //.filterNot(_.equals("none"))
    this(
      jc.getString("uniqueId"),
      jc.getString("name"),
    //  collection.immutable.List(jc.getStringList("dependsOn")),
      jc.getStringList("dependsOn").asScala.toList,
      jc.getStringList("dependsOn").asScala.toSet,
      false )

  }


}
