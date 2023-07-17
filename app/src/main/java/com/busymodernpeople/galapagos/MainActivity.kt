package com.busymodernpeople.galapagos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.galapagos.ui.component.defaultButton
import com.busymodernpeople.galapagos.ui.component.defaultTextField
import com.busymodernpeople.galapagos.ui.component.floatingButton
import com.busymodernpeople.galapagos.ui.component.textFieldWithButton
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