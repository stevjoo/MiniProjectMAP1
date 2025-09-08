package com.example.miniprojectmap1

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DaftarCatatanFragment : Fragment() {

    private val notes = mutableListOf<Note>()
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daftar_catatan, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvNotes)
        val btnAddNote = view.findViewById<View>(R.id.btnAddNote)

        adapter = NoteAdapter(notes) { note ->
            // Selalu gunakan navigation component untuk konsistensi
            val action = DaftarCatatanFragmentDirections
                .actionDaftarCatatanFragmentToDetailCatatanFragment(note.content)
            findNavController().navigate(action)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        btnAddNote.setOnClickListener {
            showAddNoteDialog()
        }

        return view
    }

    private fun showAddNoteDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_note, null)

        val etTitle = dialogView.findViewById<EditText>(R.id.etNoteTitle)
        val etContent = dialogView.findViewById<EditText>(R.id.etNoteContent)

        AlertDialog.Builder(requireContext())
            .setTitle("Tambah Catatan")
            .setView(dialogView)
            .setPositiveButton("Simpan") { _, _ ->
                val title = etTitle.text.toString()
                val content = etContent.text.toString()
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    notes.add(Note(title, content))
                    adapter.notifyItemInserted(notes.size - 1)
                } else {
                    Toast.makeText(requireContext(),
                        "Judul dan isi tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}