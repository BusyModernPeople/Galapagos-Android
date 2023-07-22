package com.busymodernpeople.galapagos.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.galapagos.R
import com.busymodernpeople.galapagos.ui.component.capture.Capturable
import com.busymodernpeople.galapagos.ui.component.capture.rememberCaptureController
import com.busymodernpeople.galapagos.ui.theme.GalapagosTheme
import kotlinx.coroutines.delay

@Composable
fun BlurredComposeView(content: @Composable () -> Unit) {
    val captureController = rememberCaptureController()
    var background: ImageBitmap? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        while (true) {
            captureController.capture()
            delay(30)
        }
    }

    Box {
        Capturable(
            controller = captureController,
            onCaptured = { bitmap, _ ->
                bitmap?.let {
                    background = bitmap
                }
            }
        ) {
            content()
        }

        background?.let {
            Image(
                modifier = Modifier.blur(50.dp),
                bitmap = it,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun PreviewBlurComposeView() {
    GalapagosTheme {
        Box(contentAlignment = Alignment.Center) {
            BlurredComposeView {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.crab),
                    contentDescription = null
                )
            }
        }
    }
}