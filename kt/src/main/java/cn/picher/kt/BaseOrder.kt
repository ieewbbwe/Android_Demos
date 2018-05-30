package cn.picher.kt

import android.util.Log

/**
 * Created by picher on 2018/3/13.
 * Describe：KT 基础命令语法
 */

val name: String = "mxh"
var age = 25

fun main(args: Array<String>) {
    BaseOrder()
    //DataType()
    //classDemo()
 //   extendClassDemo()
    //test04()

}

/*
* 函数作为参数，实现String类的字符过滤
* */
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) sb.append(element)
    }
    return sb.toString()
}

fun test04() {
    println("12eafsfsfdbzzsa".filter { it in 'a'..'f' })
}

open class D {
}

class D1 : D() {
}

open class C {
    open fun D.foo() {
        println("D.foo in C")
    }

    open fun D1.foo() {
        println("D1.foo in C")
    }

    fun caller(d: D) {
        d.foo()   // 调用扩展函数
    }
}

class C1 : C() {
    override fun D.foo() {
        println("D.foo in C1")
    }

    override fun D1.foo() {
        println("D1.foo in C1")
    }
}

fun extendClassDemo() {
    C().caller(D())   // 输出 "D.foo in C"
    C1().caller(D())  // 输出 "D.foo in C1" —— 分发接收者虚拟解析
    C().caller(D1())
}

interface Humen {
    fun sleep()
    fun eat(food: String): Boolean
    fun say(word: String)
}

open class Person(name: String) {
    open var name: String= ""
        get(){
            println("get execute" + field)
            return field.toUpperCase()
        }
        set(value) {
            println("set execute" + field +"value:"+value)
            field = value
            //field = "mxh"
            //name = "set name"
        }

    var age = 0

    init {
        this.name = name
    }

    constructor(name: String,age:Int) : this(name) {
        this.age = age
    }

    open fun work() {
        println(name + "工作中")
    }
}

open class PersionA(name: String = "xiaoming", age:Int=21) {
    init {
        println("name is $name")
        println("age is $age")
    }

    constructor(name: String) : this(name,14) {
        print("次级构造器 name $name age is $age")
    }
}

class Student(name: String) : Person(name), Humen {

    override fun work() {
        println(name + "学生工作中")
    }

    override fun sleep() {
        println(name + "睡觉")
    }

    override fun eat(food: String): Boolean {
        println("吃饭")
        if (food == "meet") {
            return true
        }
        return false
    }

    override fun say(word: String) {
        println("说了一句话：" + word)

    }
}

// 类定义 构造器 类属性 get\set 主构造器 次级构造器 初始化模块
// 类继承 访问限制关键字 方法、属性复写
fun classDemo() {
    var picher = Student("picher")
    picher.work()


   /* var personA = PersionA()
    personA = PersionA("picher")*/

}

fun DataType() {
    //express()
    //arrayDemo()
    //stringDemo()
    //ifDemo()
    //cycleDemo()
}

fun cycleDemo() {

    loop@ for (i in 1..10) {
        println("外层" + i)
        for (j in 1..10) {
            println("  内层" + j)
            if (j == 5) break@loop
        }
    }
}

fun hasPrefix(x: Any) = when (x) {
    is String -> x.startsWith("prefix")
    else -> false
}

fun ifDemo() {
    //print(hasPrefix(111))

    when (4) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> { // 注意这个块
            print("x 不是 1 ，也不是 2")
        }
    }
}

fun stringDemo() {
    println("1111")
    val text = """
    多行字符串
    多行字符串"""
    println(text)
    println("1111")

    var name = "mxh"
    print(" name is $name length is ${name.length} ${'$'}")
}

fun arrayDemo() {
    //createArray()
    arrayEx()
}

fun arrayEx() {
    val student = ArrayList<String>()//Array(10,{i -> i})
    (1..10).mapTo(student) { "学生" + it }

    for (i in 1..10) {
        if (student[i] == "学生2") {
            student.add(3, "asdadsa2")
        }
        println(student[i])
    }
}

fun createArray() {
    var student = arrayListOf(1, 2)
    println(student.size)
    student.add(55)
    print(student.get(2))
}

fun express() {
    var money = 100_000_000
    System.out.print(money.toString())
}

fun BaseOrder() {
    //customerPrint("Hello Kotlin")
    //varargOrder("M","X","H")
    //lambda()
    //example()
    //nullCheck()
    //instance(1)
    //area()
    listDemo()
}

fun listDemo() {
    var strs:MutableList<String> = mutableListOf()
    for(i in 1..5){
        strs.add(i.toString())
    }
    System.out.print(""+strs.size)
    for(u in strs){
        System.out.print(""+u)
    }
}

fun area() {

    for (i in 1..4) {
        println("1..4->>" + i)
    }

    // 从4-1 降序
    for (i in 4 downTo 1) {
        println("4..1->>" + i)
    }

    for (i in 1..10 step 4) {
        println("1..10->>" + i)
    }

    //从0-10 步进5 不包括10
    for (i in 0 until 10 step 5) {
        println("1..10 until ->>" + i)
    }
}

fun instance(obj: Any) {

    customerPrint("isString->>" + (obj is String).toString())
    customerPrint("isInt->>" + (obj is Int).toString())

}



fun nullCheck() {
    val arg: String? = "111" //?表示可为null
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

fun lambda() {
    val lanm: (Int, Int) -> Int = { x, y -> x + y }
    print(lanm(2, 5))
}

fun customerPrint(str: String) {
    System.out.print(str)
}

fun customerPrintReturn(str: String): Boolean {
    System.out.print(str)
    return true
}

fun varargOrder(vararg strs: String) {
    for (v in strs) {
        System.out.print(v)
    }
}

