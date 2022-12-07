package com.hutech.coroutines

class KotlinScopeFunctions {
    var name: String ="Dev"
    var age:Int = 26
}

class Person{
    val name:String? = null
    var age:Int = 0
}


fun main(){

  val call = KotlinScopeFunctions()
  val per = Person()

    //1- with
  val afterage:Int = with(call){
        println(name)
        println(age)
        age+5
    }
    println(afterage)

    //2- apply
   /* val person = Person().apply {
        name = "Arun"
        age = 26
    }*/

    //3- let

    //here null pointer exception because name initialised as null so let help to avoid null pointer exception
    /*println(per.name!!.reversed())
    println(per.name!!.length)*/

    per.name?.let {
        //here name is null but it not show null pointer exception
        println(it.reversed())
        println(it.length)
    }

    //4- also

    //also user for additional operation on particular object after initialised it

    val numberlist: MutableList<Int> = mutableListOf(1,2,3)

    numberlist.also {
        println("list are 2: $it")
        it.add(4)
        println("After adding 2: $it")
        it.remove(1)
        println("After remove 2: $it")
    }

    //5- run is also another scope function it is a combination of let & with


}