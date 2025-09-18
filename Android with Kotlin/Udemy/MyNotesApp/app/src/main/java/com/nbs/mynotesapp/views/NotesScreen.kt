package com.nbs.mynotesapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nbs.mynotesapp.models.Note
import com.nbs.mynotesapp.viewmodels.NotesViewModel
import com.nbs.subsriptionapp.custom.CustomSpacer
import com.nbs.subsriptionapp.data.Constants
import java.util.Date

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    notesViewModel: NotesViewModel
) {
    val notes by notesViewModel.allNotes.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(notes.size) { index ->
            NoteItem(note = notes[index])
        }
    }
}

@Composable
fun NoteItem(note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = //MaterialTheme.colorScheme.surface
                Color(note.color)
        ),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                note.title,
                style = MaterialTheme.typography.titleMedium
            )
            CustomSpacer(height = 8.dp)
            Text(
                text = note.description,
                style = MaterialTheme.typography.bodyMedium
            )
            CustomSpacer(height = 2.dp)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = Constants.dateTimeFormat.format(Date(note.timestamp)),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
        }
    }
}