package com.example.hellokmpdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kmplib.KmpGreeting
import org.jetbrains.compose.resources.stringResource

import kmp_lib.generated.resources.Res as KmpLibRes
import kmp_lib.generated.resources.kmp_lib_name

import shared.generated.resources.Res
import shared.generated.resources.cast_skill

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
        InnerGreetingView(KmpGreeting().greet())
        InnerGreetingView(text = stringResource(Res.string.cast_skill))
        InnerGreetingView(text = stringResource(KmpLibRes.string.kmp_lib_name))
    }
}

@Composable
fun InnerGreetingView(text: String) {
    Text(text = text, modifier = Modifier.padding(16.dp).then(Modifier.background(Color.LightGray)))
}