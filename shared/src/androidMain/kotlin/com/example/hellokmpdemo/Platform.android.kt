package com.example.hellokmpdemo

import com.example.kmplib.KmpGreeting
import com.example.kmplib.kmpGetPlatform

class AndroidPlatform : Platform {
    override val name: String = "Android === ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

fun hello() {
    kmpGetPlatform()

    KmpGreeting()
}
