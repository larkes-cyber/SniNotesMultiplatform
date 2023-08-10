package com.example.kmpsninotes.android.presentation.screen.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kmpsninotes.android.R
import com.example.kmpsninotes.android.presentation.navigation.Screen
import com.example.kmpsninotes.android.presentation.theme.AppTheme
import com.example.kmpsninotes.android.presentation.views.TaskItemView
import com.example.kmpsninotes.android.presentation.views.NotesPrimaryButton
import com.example.kmpsninotes.android.until.Constants.AppName
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel
) {

    val notesUiState by viewModel.notesUiState.collectAsState()
    val userUiState by viewModel.userUiState.collectAsState()
    val refreshingUiState by viewModel.refreshingUiState.collectAsState()

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()



    LaunchedEffect(userUiState.hasBeenQuit){
        if(userUiState.hasBeenQuit){
            navController.navigate(Screen.LoginScreen.route)
        }
    }

    Scaffold(

        modifier = Modifier.fillMaxWidth(),
        backgroundColor = AppTheme.colors.primaryBackground,
        scaffoldState = scaffoldState,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    modifier = Modifier
                        .width(18.dp)
                        .height(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "",
                        modifier = Modifier
                            .width(18.dp)
                            .height(12.dp),
                        tint = AppTheme.colors.primaryTitleColor
                    )
                }
                Text(
                    text = AppName,
                    style = MaterialTheme.typography.h6.copy(
                        color = AppTheme.colors.primaryTitleColor,
                        fontSize = 22.sp
                    )
                )
                if(notesUiState.selectingMode) {
                    IconButton(
                        onClick = {
                            viewModel.deleteNotes()
                        },
                        modifier = Modifier
                            .size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp),
                            tint = AppTheme.colors.primaryTitleColor
                        )
                    }
                }else Box(
                    Modifier
                        .size(24.dp)
                        .background(Color.Transparent))
            }
        },
        drawerContent = {
              Column(
                  modifier = Modifier
                      .fillMaxSize()
                      .padding(top = 20.dp)
                  ,
                  verticalArrangement = Arrangement.spacedBy(30.dp),
                  horizontalAlignment = Alignment.CenterHorizontally
              ) {
                  if(userUiState.user != null) {
                      Text(
                          text = userUiState.user!!.name,
                          style = MaterialTheme.typography.h5.copy(color = AppTheme.colors.primaryTitleColor)
                      )
                  }
                  NotesPrimaryButton(
                      text = "quit",
                      modifier = Modifier
                          .height(35.dp)
                          .width(85.dp),
                      shape = 7
                  ) {
                    viewModel.quitApp()
                  }
              }
        },
        floatingActionButton = {
            Button(
                onClick = {
                   navController.navigate(Screen.NoteDetailScreen.withArgs("null"))
                },
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.elevation(3.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.secondPrimaryBackground)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "",
                        tint = AppTheme.colors.primary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    ) {
        SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = refreshingUiState), onRefresh = { viewModel.refreshData() }) {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 18.dp)
            ){
                item {
                    Spacer(modifier = Modifier.height(45.dp))
                }
                items(notesUiState.notesList.filter { it.visible }, {it.id!!}){ note ->
                    TaskItemView(
                        title = note.title,
                        text = note.text,
                        color = Color(note.color),
                        selected = note in notesUiState.selectedNotes,
                        online_sync = note.online_sync,
                        onLongClick = {
                            if(!notesUiState.selectingMode){
                                viewModel.switchSelectingMode(true)
                                viewModel.selectNote(note)
                            }
                        }
                    ){
                        if(notesUiState.selectingMode) {
                            if(note !in notesUiState.selectedNotes) viewModel.selectNote(note)
                            else viewModel.unselectNote(note)
                        }
                        else navController.navigate(Screen.NoteDetailScreen.withArgs(note.id.toString()))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
    
}