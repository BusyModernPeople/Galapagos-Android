package com.busymodernpeople.galapagos.ui.component

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.busymodernpeople.galapagos.R
import com.busymodernpeople.galapagos.ui.theme.*

val tag = "COMPONENT-TEST"

/*
@Composable
fun defaultTextField(
    modifier: Modifier = Modifier,
    hint: String = "",
    height: Int = 56,
    maxChar: Int = 10000,
    isError: Boolean = false,
    onValueChange: (String) -> Unit = { },
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = Typography.title4,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val tag = "COMPONENT-TEST"

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    // Focus를 확인하기 위함
    val isFocused by rememberSaveable { mutableStateOf(false) }
    // X 버튼을 눌렀는지 확인하기 위함
    var isDeleted by rememberSaveable { mutableStateOf(false) }

    // TextFieldValue에 rememberSaveable를 사용하기 위함
    var textValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }

    var testText by rememberSaveable { mutableStateOf("") }
    var verticalPadding: Dp = 0.dp

    if (height == 68) verticalPadding = 20.dp
    else if (height == 56) verticalPadding = 11.dp

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth().border(width = 1.dp, color = BgGray5, shape = RoundedCornerShape(8.dp))
    ) {
        val (textField, img) = createRefs()

        BasicTextField(
            value = textValue,
            onValueChange = {
                onValueChange(it.text)
                textValue = it
            },
            modifier = modifier.background(color = Color.Cyan).focusRequester(focusRequester).focusable()
                .onFocusChanged {
                    if (it.isFocused) {
                        Log.d(tag, "Focus Changed In")
                    } else {
                        Log.d(tag, "Focus Changed Out")
                    }
                }
                .constrainAs(textField) {
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(img.start, margin = 20.dp)
                    top.linkTo(parent.top, margin = verticalPadding)
                    bottom.linkTo(parent.bottom, margin = verticalPadding)
                    width = Dimension.fillToConstraints
                },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.background(color = Color.Green),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (textValue.text.isEmpty() || isDeleted) {
                        textValue = TextFieldValue()
                        Text(hint, color = BgGray5)
                        isDeleted = false
                    }

                    innerTextField()

                    Image(modifier = Modifier.background(color = Color.Yellow).clickable {
                        focusManager.clearFocus()
                        isDeleted = !isDeleted
                        Log.d(tag, "isDeleted = $isDeleted")
                        Log.d(tag, "Click")
                    }.focusable(), painter = painterResource(id = R.drawable.icon_24px), contentDescription = null)
                }
            }
        )
//        Image(modifier = Modifier.constrainAs(img) {
//            end.linkTo(parent.end, margin = 20.dp)
//            top.linkTo(textField.top)
//            bottom.linkTo(textField.bottom)
//        }.clickable {
//            focusManager.clearFocus()
//            isDeleted = !isDeleted
//            Log.d(tag, "isDeleted = $isDeleted")
//            Log.d(tag, "Click")
//        }.focusable(), painter = painterResource(id = R.drawable.icon_24px), contentDescription = null)
    }
}
 */

@Composable
fun defaultTextField(
    modifier: Modifier = Modifier,
    hint: String = "",
    height: Int = 56,
    maxChar: Int = 10000,
    isError: Boolean = false,
    onValueChange: (String) -> Unit = { },
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = Typography.title4,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    // Focus를 확인하기 위함
    val isFocused by rememberSaveable { mutableStateOf(false) }
    // X 버튼을 눌렀는지 확인하기 위함
    var isDeleted by rememberSaveable { mutableStateOf(false) }

    // TextFieldValue에 rememberSaveable를 사용하기 위함
    var textValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    var verticalPadding: Dp = 0.dp

    if (height == 68) verticalPadding = 20.dp
    else if (height == 56) verticalPadding = 11.dp

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth().border(width = 1.dp, color = BgGray5, shape = RoundedCornerShape(8.dp))
    ) {
        val (textField, img) = createRefs()

        BasicTextField(
            value = textValue,
            onValueChange = {
                onValueChange(it.text)
                textValue = it
            },
            modifier = modifier.background(color = Color.Cyan).focusRequester(focusRequester).focusable()
                .onFocusChanged {
                    if (it.isFocused) {
                        Log.d(tag, "Focus Changed In")
                    } else {
                        Log.d(tag, "Focus Changed Out")
                    }
                }
                .constrainAs(textField) {
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(img.start, margin = 20.dp)
                    top.linkTo(parent.top, margin = verticalPadding)
                    bottom.linkTo(parent.bottom, margin = verticalPadding)
                    width = Dimension.fillToConstraints
                },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (textValue.text.isEmpty() || isDeleted) {
                        textValue = TextFieldValue()
                        Text(hint, color = BgGray5)
                        isDeleted = false
                    }

                    innerTextField()
                }
            }
        )
        Image(modifier = Modifier.constrainAs(img) {
            end.linkTo(parent.end, margin = 20.dp)
            top.linkTo(textField.top)
            bottom.linkTo(textField.bottom)
        }.clickable {
            focusManager.clearFocus()
            isDeleted = !isDeleted
        }.focusable(), painter = painterResource(id = R.drawable.ic_textfield_x), contentDescription = null)
    }
}

@Composable
fun textFieldWithButton(
    modifier: Modifier = Modifier,
    hint: String = "",
    maxChar: Int = 10000,
    isError: Boolean = false,
    onValueChange: (String) -> Unit = { },
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = Typography.title4,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var textValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    var timer by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("0")) }

    BasicTextField(
        value = textValue,
        onValueChange = {
            onValueChange(it.text)
            textValue = it
        },
        modifier = modifier.focusRequester(focusRequester).focusable()
            .onFocusChanged {
                if (it.isFocused) {
                    Log.d(tag, "Focus Changed In")
                } else {
                    Log.d(tag, "Focus Changed Out")
                }
            },
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        decorationBox = { innerTextField ->
            Column(modifier = Modifier.border(width = 1.dp, color = BgGray5, shape = RoundedCornerShape(8.dp))) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(modifier = Modifier) {
                        if (textValue.text.isEmpty()) {
                            textValue = TextFieldValue()
                            Text(hint, color = BgGray5)
                        }

                        innerTextField()
                    }

                    Text(modifier = Modifier.background(color = Color.Yellow), text = timer.text, color = PrimaryGreen, style = Typography.body1)
                    timer = TextFieldValue((timer.text.toInt() - 1).toString())
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    )
}

private fun timer(time: Int) {
    var minute = time / 60
    var second = time % 60
}