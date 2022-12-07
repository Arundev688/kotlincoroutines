package com.hutech.coroutines

import kotlinx.coroutines.*

//to create global coroutines
//if you forget to close global coroutine its running on background & lot of memory consuming
//GlobalScope.launch{
//  ex: file download, playmusic
// }

//to create local coroutines
//launch{
// it is closed once the particular activity is closed
//ex : login operation
// }



 fun main() = runBlocking {
     //if runblocking is run on thread T1
    println("Main program starts${Thread.currentThread().name}")

     // coroutines with launch
   val job:Job = launch {
        //the child thread also run on thread T1
      println("Fake work started ${Thread.currentThread().name}")
      delay(1000)
        println("Fake work ended ${Thread.currentThread().name}")
    }
    // delay(2000) // don't use delay inside coroutines
     job.join() //don't hardcord time duration join will handle that wating time
    // job.cancel() - is used to cancel the coroutines
     println("Main program ends${Thread.currentThread().name}")


     // coroutines with async
     //using async function we can return some data
     val jobDeffered:Deferred<Int> = async {
         //the child thread also run on thread T1
         println("Fake work started ${Thread.currentThread().name}")
         delay(1000)
         println("Fake work ended ${Thread.currentThread().name}")
         15
     }

     //both are susbending function we can use any one here
     val v =  jobDeffered.await() // using this we can retrive the data (that 15)
     //jobDeffered.join()


     println("Main program ends${Thread.currentThread().name}")


}