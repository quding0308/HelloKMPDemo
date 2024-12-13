package com.example.hellokmpdemo

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

import kotlin.experimental.ExperimentalObjCName

fun ComposeEntryPoint(): UIViewController =
    ComposeUIViewController {
//        ComposeApp()
        App()
    }

@Composable
private fun ComposeApp() {
    App()
}

@OptIn(ExperimentalObjCName::class)
@ObjCName(swiftName = "MyKotlinClass11")
class MyKotlinClass {
    fun myFunction(): String {
        return "Hello from Kotlin"
    }
}
