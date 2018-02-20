package com.mastercoder.examples

/**
  * Created by UNIVERSE on 2/19/18.
  * Collections can be mutable or immutable
  * Collection can be strict or Lazy
  * Collection Types
  *   1.Set
  *   2.Seq
  *     Seq can be index or linear
  *   3.Map
  * CRUD operations on Collections
  * Collection Functions
  *   1.Sort Collections using sortBy function
  *   2.transform one collection to another type
  *   3.interesting "groupBy" collection function
  *
  */
object CollectionsExamples extends App {

  case class Person(name:String, address:String, location:String)

  val pList = List.empty[Person]
  val ptmp = Person("name1", "ad1", "c1") :: pList

  ptmp.foreach(println)



}
