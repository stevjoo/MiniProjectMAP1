package com.example.miniprojectmap1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CatatanViewModel : ViewModel() {
    private val _notes = MutableLiveData<MutableList<Note>>(mutableListOf())
    val notes: LiveData<MutableList<Note>> get() = _notes

    init {
        // Inisialisasi dengan data dummy untuk testing
        viewModelScope.launch {
            val initialNotes = mutableListOf(
                Note("Contoh Catatan 1", "Ini adalah contoh catatan pertama"),
                Note("Contoh Catatan 2", "Ini adalah contoh catatan kedua")
            )
            _notes.value = initialNotes
        }
    }

    fun tambahCatatan(note: Note) {
        val currentNotes = _notes.value ?: mutableListOf()
        val newList = mutableListOf<Note>()
        newList.addAll(currentNotes)
        newList.add(note)
        _notes.value = newList
    }

    fun hapusCatatan(position: Int) {
        val currentNotes = _notes.value ?: mutableListOf()
        if (position in 0 until currentNotes.size) {
            val newList = mutableListOf<Note>()
            newList.addAll(currentNotes)
            newList.removeAt(position)
            _notes.value = newList
        }
    }
}