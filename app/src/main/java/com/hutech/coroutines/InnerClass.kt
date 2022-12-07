package com.hutech.coroutines

class Outerclass {

    var s1 = "Outer Class"

    class InnerClass{
        var s2 = "Inner Class"
        fun nestedFunction():String{
            var s3 = s2
            return s3
        }
    }

}

fun main(){
    val inner = Outerclass.InnerClass()
    println(inner.nestedFunction())
}