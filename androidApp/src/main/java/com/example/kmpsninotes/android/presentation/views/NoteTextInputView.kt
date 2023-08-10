package com.example.kmpsninotes.android.presentation.views

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.sp
import com.example.kmpsninotes.android.presentation.theme.AppTheme

@Composable
fun NoteTextInputView(
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
            textStyle = MaterialTheme.typography.body1.copy(fontSize = 14.sp, color = AppTheme.colors.primarySubtitleColor),
            cursorBrush = SolidColor(AppTheme.colors.primaryTitleColor)
        )
}