package com.example.kmpsninotes.android.presentation.views

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.kmpsninotes.android.presentation.theme.AppTheme

@Composable
fun NoteTitleInputView(
    modifier: Modifier = Modifier,
    title:String,
    onChange:(String) -> Unit
) {

    BasicTextField(
        value = title,
        onValueChange = {
            onChange(it)
        },
        modifier = modifier,
        textStyle = MaterialTheme.typography.h5.copy(fontSize = 28.sp, color = AppTheme.colors.primaryTitleColor),
        cursorBrush = SolidColor(AppTheme.colors.primaryTitleColor)
    )

}