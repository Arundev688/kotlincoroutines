package com.hutech.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//coroutines functions execution  like sequentially or concurrent(parallel)
//default coroutines execute sequentially top to bottom

fun main() = runBlocking {    // Creates a blocking coroutine that executes in current thread (main)

    println("Main program starts: ${Thread.currentThread().name}")  // main thread

    //1- sequentially
    val time = measureTimeMillis {
        val msgOne = getMessageOne()
        val msgTwo = getMessageTwo()
        println("The entire message is: ${msgOne + msgTwo}")
    }
    //output Completed in 2022 ms - because it take 2 sec to execute,sequentially  one complete another

    //2- parallel
    val time2 = measureTimeMillis {
        val msgOne:Deferred<String>  = async {getMessageOne()}
        val msgTwo:Deferred<String> = async {getMessageTwo()}
        println("The entire message is: ${msgOne.await() + msgTwo.await()}")
    }
    //output Completed in 1033 ms - because async helps to run both coroutines in same time

    //3- lazy
    val msgOne:Deferred<String>  = async(start = CoroutineStart.LAZY) {getMessageOne()}
    val msgTwo:Deferred<String> =  async(start = CoroutineStart.LAZY) {getMessageTwo()}
    println("The entire message is: ${msgOne.await() + msgTwo.await()}")

   // println("Completed in $time2 ms")
    println("Main program ends: ${Thread.currentThread().name}")    // main thread
}

suspend fun getMessageOne(): String {
    delay(1000L)    // pretend to do some work
    return "Hello "
}

suspend fun getMessageTwo(): String {
    delay(1000L)    // pretend to do some work
    return "World!"
}