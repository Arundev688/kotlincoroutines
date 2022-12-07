package com.hutech.coroutines


fun main(){
    var immutableSet = setOf(6,9,9,0,0,"first","second")
    // gives compile time error
    // immutableSet.add(7)
    /*for(item in immutableSet){
        println(item)
    }*/
    println(immutableSet)
}