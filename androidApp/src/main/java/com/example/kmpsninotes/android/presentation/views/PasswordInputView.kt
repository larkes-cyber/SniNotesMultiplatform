package com.example.kmpsninotes.android.presentation.views


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.kmpsninotes.android.R
import com.example.kmpsninotes.android.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordInputView(
    text:String,
    modifier: Modifier = Modifier,
    onChange:(String) -> Unit
) {

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    OutlinedTextField(
        value = text,
        onValueChange = {onChange(it)},
        placeholder = { Text(
            text = "Password",
            color = AppTheme.colors.primaryTitleColor.copy(alpha = 0.6f)
        )},
        label = { Text(
            text = "Enter your password",
            color = AppTheme.colors.primaryTitleColor.copy(alpha = 0.6f)
        ) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible.value)
                R.drawable.baseline_visibility_24
            else R.drawable.baseline_visibility_off_24
            val description = if (passwordVisible.value) "Hide password" else "Show password"

            IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
                Icon(
                    painter = painterResource(id = image),
                    description,
                    tint = AppTheme.colors.primaryTitleColor
                )
            }
        },
        modifier = modifier
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusEvent {
                if (it.isFocused) {
                    scope.launch {
                        delay(200)
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = AppTheme.colors.primaryTitleColor,
            textColor = AppTheme.colors.primaryTitleColor,
            unfocusedIndicatorColor = AppTheme.colors.primaryTitleColor
        ),
        shape = RoundedCornerShape(6.dp)
    )

}