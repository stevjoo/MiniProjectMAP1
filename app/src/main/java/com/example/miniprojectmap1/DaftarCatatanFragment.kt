package com.example.miniprojectmap1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DaftarCatatanFragment : Fragment() {

    private val viewModel: CatatanViewModel by activityViewModels()
    private lateinit var adapter: NoteAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daftar_catatan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvNotes)
        val btnAddNote = view.findViewById<View>(R.id.btnAddNote)

        setupRecyclerView()

        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            adapter.updateData(notes)
        }

        btnAddNote.setOnClickListener {
            showAddNoteDialog()
        }
    }

    private fun setupRecyclerView() {
        adapter = NoteAdapter(emptyList()) { note ->
            val isiCatatan = note.content
            val detailFragment = DetailCatatanFragment().apply {
                arguments = Bundle().apply {
                    putString("isiCatatan", isiCatatan)
                }
            }

            val activity = requireActivity()

            // Cek apakah ada container detail (dual-pane / tablet)
            if (activity.findViewById<View>(R.id.detail_catatan_container) != null) {
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.detail_catatan_container, detailFragment)
                    .commit()
            } else {
                // Safe Args navigasi kalau single-pane (HP / portrait)
                val action =
                    DaftarCatatanFragmentDirections
                        .actionDaftarCatatanFragmentToDetailCatatanFragment(isiCatatan)
                findNavController().navigate(action)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun showAddNoteDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_note, null)

        val etTitle = dialogView.findViewById<EditText>(R.id.etNoteTitle)
        val etContent = dialogView.findViewById<EditText>(R.id.etNoteContent)

        android.app.AlertDialog.Builder(requireContext())
            .setTitle("Tambah Catatan")
            .setView(dialogView)
            .setPositiveButton("Simpan") { _, _ ->
                val title = etTitle.text.toString()
                val content = etContent.text.toString()
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    viewModel.tambahCatatan(Note(title, content))
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Judul dan isi tidak boleh kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}
