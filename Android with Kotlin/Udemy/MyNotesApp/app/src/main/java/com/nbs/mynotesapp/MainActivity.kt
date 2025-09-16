package com.nbs.mynotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nbs.chatroomapp.views.custom.AppBar
import com.nbs.mynotesapp.database.AppDatabase
import com.nbs.mynotesapp.models.Note
import com.nbs.mynotesapp.repositories.NoteRepository
import com.nbs.mynotesapp.ui.theme.MyNotesAppTheme
import com.nbs.mynotesapp.viewmodels.NotesViewModel
import com.nbs.mynotesapp.views.AddNoteScreen
import com.nbs.mynotesapp.views.NotesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyNotesAppTheme {
                val notesViewModel: NotesViewModel = viewModel()
                val notes by notesViewModel.allNotes.collectAsState(initial = emptyList())

                var showAddNoteDialog by remember { mutableStateOf(false) }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .navigationBarsPadding(),
                        topBar = {
                            AppBar(
                                title = "My Notes",
                                navIcon = { },
                                actionIcons = {}
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                {
                                    showAddNoteDialog = true
                                },
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Note"
                                )

                            }
                        }
                    ) { paddingValues ->
                        NotesScreen(Modifier.padding(paddingValues), notes)
                        AddNoteScreen(
                            showDialog = showAddNoteDialog,
                            onDismiss = { showAddNoteDialog = false },
                            onNoteAdded = { newNote ->
                                notesViewModel.insertNote(newNote)
                                showAddNoteDialog = false
                            }
                        )
                    }
                }
            }
        }
    }
}

