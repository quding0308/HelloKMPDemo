package com.example.hellokmpdemo

import hellokmpdemo.shared.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import platform.Foundation.NSBundle

@OptIn(ExperimentalResourceApi::class)
actual fun getLocalFilePathFor(item: String): String {
//    val filePath = NSBundle.mainBundle.pathForResource(item, null)

    // 可以正常工作，但最好不要写死path路径。路径依赖了 KMP Res.getUrl 函数内部的实现，后续可能会被修改
//    val path = "compose-resources/composeResources/hellokmpdemo.shared.generated.resources/files"
//    val filePath = NSBundle.mainBundle.pathForResource("$path/$item", null)

    // 删除scheme file:// 以获取文件的路径
    val fileUrlPath = Res.getUri("files/${item}")
    val filePath = fileUrlPath.replace("file://", "")

    return filePath ?: ""
}