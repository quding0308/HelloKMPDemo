package com.example.hellokmpdemo

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

fun kotlinStudy() {
    println("Hello Kotlin Multiplatform!")

    // 声明变量
    val x = 5
    var y = 6
    var z: Int = 7
    var name: String = "Fred"
    //val p: String by lazy { // 懒加载，第一次被调用时会计算一次
    //    return "111"
    //}

    if (x < y) {
        println("x < y")
    } else {
        println("x > y")
    }


    testMap()
    testList()

    GlobalScope.launch{
        testCoroutine()
    }
    println("=== end")

    // 自动解包
    val intValue = parseInt("11")
    if (intValue is Int) { // 类型检测完后，tmp 就是 Int 类型。自动解包
        println(intValue)
    }

    if (intValue !is Int) {
        println("intValue is null")
    }

    hello(name)

    val rect = Rectangle(5.0, 2.0)
    print("周长：${rect.perimeter} \n")
}

// 声明函数，Unit等价于不返回值
private fun hello(msg: String) {
    print("hello, ${msg} \n")
}

// optional
private fun parseInt(str: String): Int? {
    return null
}

// 定义class
private class Rectangle(val width: Double, val height: Double) {
    val perimeter = (width + height) * 2
}

// map 相关操作
private fun testMap() {
    val map = mutableMapOf("a" to 1, "b" to 2, "c" to 3)
    map.get("a")    // get value 等价于下面。如果 key 不存在，则返回 null
    map["a"]    // get value 简写
    map.getOrElse("d") { 4 } // 如果 key 不存在，则返回默认值 4
    map.keys // 所有key
    map.values // 所有 value

    // 添加元素
    map.put("d", 4) // 添加元素
    map["e"] = 5 // 添加元素
    map.putAll(mapOf("f" to 6, "g" to 7)) // 添加多个元素

    // 删除元素
    map.remove("a") // 删除元素

    // 过滤
    val newMap = map.filter { (k, v) -> v > 1 } // 过滤   // it 是 iterator 的简写，代表一个元素

    for ((k,v) in map) {
        println("$k -> $v")
    }
}

private fun testList() {
    // 数组，遍历
    val items = mutableListOf("1", "2", "3", "4")
    for (item in items) {
        println(item)
    }

    items.get(0) // 获取元素
    items.first()
    items.last()
    items.getOrElse(5) { "5" } // 如果 index 不存在，则返回默认值 5

    items.add("5") // 添加元素
    items.addAll(listOf("6", "7")) // 添加多个元素

    items[0] = "0" // 修改元素

    // 只会执行一条分支
    when {
        "1" in items -> println("1111")
        "2" in items -> println("2222")
    }
}

private suspend fun testCoroutine() {
    runBlocking {
        launch {
            delay(1000)
            println("World!")
        }

        println("Hello,")
    }

    val runBlockingJob = runBlocking {
        println("=== runBlocking启动一个协程")
        41
    }
    println("===  runBlockingJob $runBlockingJob")

    val launchJob = GlobalScope.launch{
        delay(2000)
        println("===  launch 启动一个协程")
    }
    println("===  launchJob $launchJob")


    val asyncJob = GlobalScope.async{
        delay(1000)
        println("===  async 启动一个协程")
        "我是返回值"
    }
    println("=== asyncJob $asyncJob")

    val result = asyncJob.await()

    println("=== result $result")

    // io 线程
    val result1 = withContext(Dispatchers.IO) {
        //
        "withContext_result"
    }

    println("=== result1 $result1")

    val scope = CoroutineScope(Dispatchers.Main + CoroutineName("testCoroutine"))

    scope.launch {
        println("===  scope.launch 启动一个协程")


    }


}

private suspend fun testCoroutine2() {
    withContext(Dispatchers.IO) {
        delay(1000)
        println("World!")
    }

    // 
    coroutineScope {
        launch {
            delay(1000)
        }
    }

    GlobalScope.launch {
        //
    }

    GlobalScope.async {
        //
    }

    // io 线程
    withContext(Dispatchers.IO) {
        //
    }

    // 主线程
    withContext(Dispatchers.Main) {
        //
    }


}
