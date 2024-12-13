package com.example.hellokmpdemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import chaintech.videoplayer.model.AudioFile
import chaintech.videoplayer.model.PlayerConfig
import chaintech.videoplayer.ui.audio.AudioPlayerComposable
import chaintech.videoplayer.ui.video.VideoPlayerComposable
import hellokmpdemo.shared.generated.resources.Res
import hellokmpdemo.shared.generated.resources.app_name
import hellokmpdemo.shared.generated.resources.attack1_5
import hellokmpdemo.shared.generated.resources.bg_1
import hellokmpdemo.shared.generated.resources.bg_event_48
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    kotlinStudy()

    LazyColumn {
        item {
            val openAlertDialog = remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                println("LaunchedEffect")
            }

            Button(modifier = Modifier.padding(50.dp),onClick = {
                openAlertDialog.value = true
            }) {
                Column {
                    Text("展示 Dialog")
                    Text("展示 Dialog")
                }
            }

            if (openAlertDialog.value) {
                AlertDialogExample(
                    onDismissRequest = { openAlertDialog.value = false },
                    onConfirmation = {
                        openAlertDialog.value = false
                        println("Confirmation registered") // Add logic here to handle confirmation.
                    },
                    dialogTitle = "Alert dialog example",
                    dialogText = "This is an example of an alert dialog with buttons.",
                )
            }
        }
        item {
            CustomTextView(Greeting().greet())
            CustomTextView("从资源读取：" + stringResource(Res.string.app_name))
        }
        item {
            var bytes by remember { mutableStateOf(ByteArray(0)) }
            LaunchedEffect(Unit) {
                bytes = Res.readBytes("files/hello.txt")
            }
            CustomTextView("从txt文件读取" + bytes.decodeToString())
        }
        item {
            CustomTextView("png图片")
            Image(
                painter = painterResource(Res.drawable.attack1_5),
                contentDescription = "png"
            )
        }
        item {
            CustomTextView("jpg图片")
            Image(
                painter = painterResource(Res.drawable.bg_event_48),
                contentDescription = "jpg"

            )
        }
        item {
            CustomTextView("webp图片")
            Image(
                painter = painterResource(Res.drawable.bg_1),
                contentDescription = "webp"
            )
        }
        item {
            CustomTextView("播放本地mp3")

            val filePath = getLocalFilePathFor("audio_1.mp3")
            println(filePath)
            val audioList = listOf(
                AudioFile(
                    audioUrl = filePath,
                    audioTitle = "111",
                    thumbnailUrl = ""
                )
            )

            AudioPlayerComposable(
//                modifier = Modifier.width(1.dp).height(1.dp),
                audios = audioList
            )
        }
        item {
            CustomTextView("播放本地mp4")

            val videoUrl = getLocalFilePathFor("game_bg.mp4")
            println("videoUrl $videoUrl")

            VideoPlayerComposable(
                modifier = Modifier.width(200.dp).height(400.dp),
                url = videoUrl,
                playerConfig = PlayerConfig(
                    isDurationVisible = false,
                    isSeekBarVisible = false,
                    isPauseResumeEnabled = false,
                    isMuteControlEnabled = false,
                    isScreenResizeEnabled = false,
                    isSpeedControlEnabled = false,
                    isScreenLockEnabled = false,
                    isAutoHideControlEnabled = false,
                    isFastForwardBackwardEnabled = false,
                    isFullScreenEnabled = false,
                )
            )
        }
    }
}

@Composable
fun CustomTextView(text: String) {
    Text(text = text, modifier = Modifier.padding(16.dp).then(Modifier.background(Color.LightGray)))
}