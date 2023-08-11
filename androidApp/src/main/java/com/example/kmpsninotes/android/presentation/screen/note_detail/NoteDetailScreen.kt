package com.example.kmpsninotes.android.presentation.screen.note_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kmpsninotes.android.presentation.theme.AppTheme
import com.example.kmpsninotes.android.R
import com.example.kmpsninotes.android.presentation.navigation.Screen
import com.example.kmpsninotes.android.presentation.views.NoteTextInputView
import com.example.kmpsninotes.android.presentation.views.NoteTitleInputView

@Composable
fun NoteDetailScreen(
    navController: NavController,
    viewModel: NoteDetailViewModel,
    id:String?
) {

    val uiState by viewModel.uiState.collectAsState()

   LaunchedEffect(uiState){
       if(uiState.noteHasBeenEdited){
           navController.navigate(Screen.NotesScreen.route)
       }
   }

    Scaffold(
        backgroundColor = Color(uiState.color),
        topBar = {
           Row(
               horizontalArrangement = Arrangement.SpaceBetween,
               modifier = Modifier
                   .padding(horizontal = 16.dp)
                   .padding(top = 20.dp)
                   .fillMaxWidth()
           ) {
                IconButton(
                    onClick = {
                        viewModel.databaseSync()
                    },
                    modifier = Modifier.size(25.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.size(25.dp),
                        tint = AppTheme.colors.primarySubtitleColor
                    )
                }
               Row(
                   horizontalArrangement = Arrangement.spacedBy(25.dp)
               ) {
                   IconButton(
                       onClick = {  },
                       modifier = Modifier.size(22.dp)
                   ) {
                       Icon(
                           painterResource(id = R.drawable.baseline_push_pin_24),
                           contentDescription = "",
                           modifier = Modifier.size(22.dp),
                           tint = AppTheme.colors.primarySubtitleColor
                       )
                   }
                   IconButton(
                       onClick = {  },
                       modifier = Modifier.size(18.dp)
                   ) {
                       Icon(
                           painterResource(id = R.drawable.menu_icon),
                           contentDescription = "",
                           modifier = Modifier.size(18.dp),
                           tint = AppTheme.colors.primarySubtitleColor
                       )
                   }
               }

           }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(top = 40.dp)
                .padding(start = 20.dp, end = 12.dp)
        ) {
            NoteTitleInputView(title = uiState.title){
                viewModel.onTitleFieldChange(it)
            }
            Spacer(modifier = Modifier.height(25.dp))
            NoteTextInputView(title = uiState.text){
                viewModel.onTextFieldChange(it)
            }
        }
    }

}