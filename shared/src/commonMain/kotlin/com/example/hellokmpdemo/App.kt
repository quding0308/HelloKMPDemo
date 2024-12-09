package com.example.hellokmpdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}

@Composable
fun App() {
    Column {
        InnerGreetingView(Greeting().greet())
        InnerGreetingView(text = Greeting().greet())
    }
}

@Composable
fun InnerGreetingView(text: String) {
    Text(text = text, modifier = Modifier.padding(16.dp).then(Modifier.background(Color.LightGray)))
}