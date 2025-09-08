package com.example.miniprojectmap1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CatatanViewModel : ViewModel() {
    private val _notes = MutableLiveData<MutableList<Note>>(mutableListOf())
    val notes: LiveData<MutableList<Note>> get() = _notes

    init {
        // Inisialisasi dengan data dummy untuk testing
        val initialNotes = mutableListOf(
            Note("Contoh Catatan 1", "Ini adalah contoh catatan pertama"),
            Note("Contoh Catatan 2", "Ini adalah contoh catatan kedua")
        )
        _notes.value = initialNotes
    }

    fun tambahCatatan(note: Note) {
        val currentNotes = _notes.value ?: mutableListOf()
        currentNotes.add(note)
        _notes.value = currentNotes
    }
}