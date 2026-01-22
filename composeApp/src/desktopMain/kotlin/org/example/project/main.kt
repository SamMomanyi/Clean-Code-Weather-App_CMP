package org.example.project

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.engine.okhttp.OkHttp
import org.example.project.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMP-WeatherApp",
    ) {
        //we can also use OkHttp here
        App(
            engine = remember {
                OkHttp.create()
            }
        )
    }
}