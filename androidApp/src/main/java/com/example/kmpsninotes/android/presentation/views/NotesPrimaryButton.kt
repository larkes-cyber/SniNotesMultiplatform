package com.example.kmpsninotes.android.presentation.views

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpsninotes.android.presentation.theme.AppTheme

@Composable
fun NotesPrimaryButton(
    modifier:Modifier = Modifier,
    text:String = "",
    shape:Int = 11,
    onClick:() -> Unit
) {

    Button(
        onClick = {
            onClick()
         },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.primary),
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(shape.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = AppTheme.colors.secondPrimaryTitleColor
        )
    }

}