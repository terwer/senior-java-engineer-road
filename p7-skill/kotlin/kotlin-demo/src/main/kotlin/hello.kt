package test

import java.util.function.Consumer

fun main(args: Array<String>){
    println("hello")

    println("--------------")
    var list: List<String> = listOf("aaa", "bbb", "ccc")

    for (str in list){
        println(str)
    }

    println("---------------")

    list.forEach(Consumer { println(it) })

    println("--------------")

    list.forEach(System.out::println)
}


