package cn.picher.kt

/**
 * Created by picher on 2018/3/13.
 * Describe：KT 基础命令语法
 */

val name : String = "mxh"
var age = 25

fun main(args: Array<String>) {
    BaseOrder()
}

fun BaseOrder() {
    //customerPrint("Hello Kotlin")
    //varargOrder("M","X","H")
    //lambda()
    //example()
    //nullCheck()
    //instance(1)
    area()
}

fun area() {

    for(i in 1..4 ){
        println("1..4->>"+i)
    }

    for(i in 4 downTo 1){
        println("4..1->>"+i)
    }

    for(i in 1..10 step 4){
        println("1..10->>"+i)
    }

    for(i in 0 until 10 step 5){
        println("1..10 until ->>"+i)
    }
}

fun instance(obj:Any) {

    customerPrint("isString->>"+(obj is String).toString())
    customerPrint("isInt->>"+(obj is Int).toString())

}

fun nullCheck() {
    val arg : String? = "111" //?表示可为null
    customerPrint(arg!!)//!! 表示为null则抛出异常
    var toint = arg?.toInt() ?: -1
    customerPrint(toint.toString())//!! 表示为null则抛出异常

}

fun example() {
    var a = 1
    // 模板中的简单名称：
    val s1 = "a is $a"

    a = 2
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    customerPrint(s2)
}

fun lambda(){
    val lanm:(Int,Int) -> Int = {x,y -> x+y}
    print(lanm(2,5))
}

fun customerPrint(str: String) {
    System.out.print(str)
}

fun customerPrintReturn(str:String):Boolean{
    System.out.print(str)
    return true
}

fun varargOrder(vararg strs:String){
    for (v in strs){
        System.out.print(v)
    }
}

