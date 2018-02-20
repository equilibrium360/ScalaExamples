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

  //Adding elements to List
  val pList = List.empty[Person]
  val ptmp = Person("name1", "ad1", "c1") :: pList

  val ptmp2 = Person("name2", "ad1", "c2") :: ptmp

  val ptmp3 = ptmp2 :+ Person("fname3", "ad3", "c3")

  ptmp3.foreach(println)

  //Sorting elements in List
  val sortedPersons = ptmp3.sortBy(_.name)

  println("Sorted Persons")
  sortedPersons.foreach(println)

  //transform collection
   val pset:Set[String] = sortedPersons.map(p => p.address).toSet

  println("Transformed Persons")
  pset.foreach(println)

  //GroupBy Example

  val groupedPersons = sortedPersons.groupBy(_.name.head)
  println("Grouped Persons")
  groupedPersons.foreach(println)

 // Using Forloop on Collection

  val pairs = for {
    sperson <- sortedPersons
    sloc <- sperson.location
  } yield (sperson, sloc)

  println("pairs"+ pairs)





}
