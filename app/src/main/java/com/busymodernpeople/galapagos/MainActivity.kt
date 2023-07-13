package com.busymodernpeople.galapagos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.busymodernpeople.galapagos.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalapagosTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(style = Typography.title1, text = "Android")
                        Text(style = Typography.title2, text = "Android")
                        Text(style = Typography.title3, text = "Android")
                        Text(style = Typography.title4, text = "Android")
                        Text(style = Typography.body1, text = "Android")
                        Text(style = Typography.body2, text = "Android")
                        Text(style = Typography.body3, text = "Android")
                        Text(style = Typography.body4, text = "Android")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GalapagosTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(style = Typography.title1, text = "Android")
                Text(style = Typography.title2, text = "Android")
                Text(style = Typography.title3, text = "Android")
                Text(style = Typography.title4, text = "Android")
                Text(style = Typography.body1, text = "Android")
                Text(style = Typography.body2, text = "Android")
                Text(style = Typography.body3, text = "Android")
                Text(style = Typography.body4, text = "Android")
            }
        }
    }
}