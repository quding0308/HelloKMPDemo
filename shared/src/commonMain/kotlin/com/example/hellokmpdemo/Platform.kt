package com.example.hellokmpdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform