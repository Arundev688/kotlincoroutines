package com.hutech.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() { //main thread
    println("Main thread start: ${Thread.currentThread().name}")

    /*  thread { //creates a background thread
          println("Background thread start : ${Thread.currentThread().name}")
          Thread.sleep(3000)
          println("Background thread end : ${Thread.currentThread().name}")
      }*/

    GlobalScope.launch {//creates a coroutine background thread
        println(" Background thread start : ${Thread.currentThread().name}")
        //Thread.sleep(3000) // here thread is blocked 3 seconds thread is busy
        delay(3000)/*recommended
                             delay is only work inside coroutines,
                             but using delay means coroutines is suspended but thread is free (not blocked)
                             */
        println("Background thread end  : ${Thread.currentThread().name}")
    }
    Thread.sleep(1000)
    println("Main thread end: ${Thread.currentThread().name}")

/*  normal otuput
    Main thread start: main
    Background thread start : Thread-0
    Main thread end: main
    Background thread end : Thread-0*/
    //In normally main thread & background thread run parallel background thread is work while main thread also end


    /*coroutine output
    Main thread start: main
    Background thread start : DefaultDispatcher-worker-1
    Main thread end: main */
    //Here coroutines works only main thread is active
    //Coroutines is not threads its execute the threads on background
}