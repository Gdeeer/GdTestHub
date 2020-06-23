package com.gdeer.gdtesthub.kotlin

// 第一个 public 可省略
// 第二个 public 可省略
// constructor() 的 constructor 可省略
// constructor() 的 () 为空时可省略
// {} 为空时可省略
public class ConstructorTest public constructor() {
}

// 省略后：
class ConstructorTest2

// constructor() 的函数体可放在 init{} 中
class ConstructorTest3 constructor() {
    init {
    }
}

// constructor() + init{} 可替换为 constructor() {}
class ConstructorTest4 {
    constructor() {}
}

// constructor 可以放入 val、var 作为成员变量
class ConstructorTest5 constructor(name: String) {
    val name = name
}

// 简化后：
class ConstructorTest6 constructor(val name: String)
class ConstructorTest7 constructor(var name: String)