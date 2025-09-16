package com.nbs.mynotesapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.mynotesapp.Graph
import com.nbs.mynotesapp.models.Note
import com.nbs.mynotesapp.repositories.NoteRepository
import kotlinx.coroutines.launch

class NotesViewModel(private val noteRepository: NoteRepository = Graph.noteRepository) :
    ViewModel() {

    val allNotes = noteRepository.allNotes

    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }
}