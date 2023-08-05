package com.busymodernpeople.galapagos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalapagosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GalapagosTheme.colors.BgGray5
                ) {
                    GalapagosNavHost()
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
                Text(style = GalapagosTheme.typography.title1, text = "Android")
                Text(style = GalapagosTheme.typography.title2, text = "Android")
                Text(style = GalapagosTheme.typography.title3, text = "Android")
                Text(style = GalapagosTheme.typography.title4, text = "Android")
                Text(style = GalapagosTheme.typography.body1, text = "Android")
                Text(style = GalapagosTheme.typography.body2, text = "Android")
                Text(style = GalapagosTheme.typography.body3, text = "Android")
                Text(style = GalapagosTheme.typography.body4, text = "Android")
            }
        }
    }
}