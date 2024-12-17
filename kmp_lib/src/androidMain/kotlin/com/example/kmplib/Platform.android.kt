package com.example.kmplib

class AndroidPlatform : Platform {
    override val name: String = "Android === ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

fun kmpGetPlatform(): Platform = AndroidPlatform()