package com.example.kmplib

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
//import kmp_lib.generated.resources.Res
//import kmp_lib.generated.resources.kmp_cast_skill
import org.jetbrains.compose.resources.stringResource
import kmp_lib.generated.resources.Res
import kmp_lib.generated.resources.kmp_cast_skill

class KmpGreeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}

@Composable
fun App() {
    Column {
        InnerGreetingView(KmpGreeting().greet())
        InnerGreetingView(text = stringResource(Res.string.kmp_cast_skill))
    }
}

@Composable
fun InnerGreetingView(text: String) {
    Text(text = text, modifier = Modifier.padding(16.dp).then(Modifier.background(Color.LightGray)))
}