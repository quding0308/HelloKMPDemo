package com.example.hellokmpdemo

import hellokmpdemo.shared.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
actual fun getLocalFilePathFor(item: String): String {
    return Res.getUri("files/${item}")
}