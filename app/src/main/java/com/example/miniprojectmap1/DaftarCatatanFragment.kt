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

// Fragment ini buat nampilin daftar catatan sama tombol tambah catatan
class DaftarCatatanFragment : Fragment() {

    // List buat nyimpan catatan
    private val notes = mutableListOf<Note>()
    private lateinit var adapter: NoteAdapter

    // Lifecycle Fragment: dipanggil pas UI fragment dibuat
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout buat fragment ini
        val view = inflater.inflate(R.layout.fragment_daftar_catatan, container, false)

        // Ambil RecyclerView sama tombol dari layout
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvNotes)
        val btnAddNote = view.findViewById<View>(R.id.btnAddNote)

        // Adapter untuk RecyclerView
        adapter = NoteAdapter(notes) { note ->
            // Cek apakah ada mode dual-pane
            val isDualPane = activity?.findViewById<View>(R.id.detail_catatan_container) != null
            if (isDualPane) {
                // kalo dual-pane -> tampilkan fragment detail di container kanan
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.detail_catatan_container,
                        DetailCatatanFragment.newInstance(note.content)) // dynamic fragment
                    ?.commit()
            } else {
                // kalo layar kecil -> pindah fragmentnya menggunakan Jetpack Navigation
                val action = DaftarCatatanFragmentDirections
                    .actionDaftarCatatanFragmentToDetailCatatanFragment(note.content)
                findNavController().navigate(action)
            }
        }

        // Atur RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Klik tombol "Tambah Catatan"
        btnAddNote.setOnClickListener {
            showAddNoteDialog()
        }

        return view
    }

    // buka dialog buat menambahkan catatan baru
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
