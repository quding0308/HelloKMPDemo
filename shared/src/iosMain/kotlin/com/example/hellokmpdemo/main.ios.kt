package com.example.hellokmpdemo

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun ComposeEntryPoint(): UIViewController =
    ComposeUIViewController {
//        ComposeApp()
        App()
    }

@Composable
private fun ComposeApp() {
    App()
}
