package com.hutech.coroutines

fun main(){
    var a: String? ="Arun" //? is allow to use null value to "var a"
                        // without this ? it show compilation error
                         // ?. id called safe calls
                       //? main use of this ? is avoid null pointer  error at run time
    a = null

    //here ?: is called safe call if a is not null print value else it show "a is null"
    println(a ?: "a is null")


    //To perform a certain operation only for non-null values,
   // you can use the safe call operator together with let
    val listWithNulls: List<String?> = listOf("Kotlin", null,"Dev")
    for (item in listWithNulls) {
        item?.let { println(it) } // prints Kotlin,Dev and ignores null
    }

    //here filterNotNull is used to Collections of a nullable list
    val nullableList: List<Int?> = listOf(1, 2, null, 4)
    val intList: List<Int> = nullableList.filterNotNull()
    println(intList)


    //The not null assertion (!!) operator converts any value to a non-null type and
    // throws an exception if the value is null.
    var i : String? = "Welcome"
    println(i!!.length)


}