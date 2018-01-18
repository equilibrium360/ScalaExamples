package com.mastercoder.examples

//1.CallByValue
//2.CallByName
object CallByValueAndNameExample extends App  {
 val doWork:(Int => Int) = (i:Int) => { println("Start doing Work")
                                 println(s"Received ${i}")
   i+100
 }

  doWork(2)

  //CallBy

  def callByName(a: => Int): Unit = {
    println("Inside Call By Name")

    println(s"A =   ${a}")
    println(s"A =   ${a}")
  }

  def callByValue(a:Int): Unit = {
    println(s"A =   ${a}")
    println(s"A =   ${a}")
  }

  callByValue(doWork(3))
  callByName(doWork(3))

}


