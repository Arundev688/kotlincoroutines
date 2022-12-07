package com.hutech.coroutines
import kotlinx.coroutines.*

//coroutines cancel
fun main() = runBlocking {
    println("Main program starts${Thread.currentThread().name}")

    //Dispatchers.Default is only needed if isActive is used
    val job:Job = launch(Dispatchers.Default) {
      for (i in 0..500){
          if (!isActive){ // 3- active
              break
          }
          println("$i")
          Thread.sleep(1)
         //1- delay(50) //now the coroutines is cancel because
                           // each  & every iteration it check our coroutines cancel or not
         //2- yield()
      }
    }



    //coroutines are only cancel if it is cooperative
    //cooperative means the coroutines must use the keywords that belongs to this package such as
    // delay(),yield(),withContext(),withTimeout()

    delay(2)
    /*job.cancel()
    job.join()*/
    //to avoid above 2 lines & write single line
    job.cancelAndJoin()




  /*  val result:String? = withTimeoutOrNull(2000){ // 4- withTimeoutOrNull
        for (i in 0..500){
            println("$i")
            delay(100)
        }
        "Task is done" // if thread not complete it return null or else return this msg
    }

    println("Result: $result")*/

    println("\n Main progeam ends: ${Thread.currentThread().name}")

}