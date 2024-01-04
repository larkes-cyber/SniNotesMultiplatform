package com.example.kmpsninotes.android.presentation.views

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import com.example.kmpsninotes.android.presentation.theme.AppTheme
import com.example.kmpsninotes.android.until.Constants.ONLINE

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItemView(
    modifier: Modifier = Modifier,
    title:String,
    text:String,
    color:Color,
    selected:Boolean,
    online_sync:Boolean,
    onLongClick:() -> Unit,
    onClick:() -> Unit
) {

    val fullTaskSizeMode = rememberSaveable {
        mutableStateOf(false)
    }




    Card(
        backgroundColor = color,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.combinedClickable(
            onClick = {
                if(selected) {
                    onClick()
                    return@combinedClickable
                }
                if(!fullTaskSizeMode.value) onClick()
                else fullTaskSizeMode.value = !fullTaskSizeMode.value
            },
            onLongClick = {
                onLongClick()
                if(!selected) fullTaskSizeMode.value = !fullTaskSizeMode.value
            }
        )
    ) {
        Row(
            modifier = if(fullTaskSizeMode.value)
                modifier
                    .padding(vertical = 14.dp)
                    .padding(start = 16.dp, end = 26.dp)
                else modifier
                .padding(vertical = 14.dp)
                .padding(start = 16.dp, end = 26.dp)
                .height(60.dp)

        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth(if (selected) 0.9f else 1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6.copy(color = AppTheme.colors.primaryTitleColor)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    if(fullTaskSizeMode.value) {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.subtitle1.copy(color = AppTheme.colors.primarySubtitleColor),
                            modifier = Modifier.fillMaxWidth(0.99f)
                        )
                    }
                    else{
                        Text(
                            text = if(text.length > 35) text.substring(0, 33) + ".." else text,
                            style = MaterialTheme.typography.subtitle1.copy(color = AppTheme.colors.primarySubtitleColor),
                            modifier = Modifier.fillMaxWidth(0.98f)
                        )
                    }
                    if(!selected) {
                        Box(
                            modifier = Modifier.fillMaxWidth(2f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .clip(RoundedCornerShape(100))
                                    .background(if(online_sync) AppTheme.colors.online_color else AppTheme.colors.offline_color)
                            ) {

                            }
                        }
                    }
                }
            }
            if(selected) {
                Box(
                    modifier = Modifier.fillMaxWidth(2f)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 3.dp, vertical = 4.dp)
                            .size(22.dp)
                            .clip(MaterialTheme.shapes.small)
                            .background(color = AppTheme.colors.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
                                .size(14.dp)
                        )
                    }
                }
            }
        }
    }


}