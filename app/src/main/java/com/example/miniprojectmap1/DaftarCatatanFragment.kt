package com.example.miniprojectmap1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class DaftarCatatanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daftar_catatan, container, false)

        val btnNote1 = view.findViewById<Button>(R.id.note_1_button)
        val btnNote2 = view.findViewById<Button>(R.id.note_2_button)

        // Cek apakah dual pane (detail_catatan_container ada)
        val isDualPane = activity?.findViewById<View>(R.id.detail_catatan_container) != null

        btnNote1.setOnClickListener {
            if (isDualPane) {
                // Update fragment kanan langsung
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.detail_catatan_container, DetailCatatanFragment.newInstance("Isi catatan 1"))
                    ?.commit()
            } else {
                // Navigate pakai Safe Args untuk HP
                val action = DaftarCatatanFragmentDirections
                    .actionDaftarCatatanFragmentToDetailCatatanFragment("Isi catatan 1")
                findNavController().navigate(action)
            }
        }

        btnNote2.setOnClickListener {
            if (isDualPane) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.detail_catatan_container, DetailCatatanFragment.newInstance("Isi catatan 2"))
                    ?.commit()
            } else {
                val action = DaftarCatatanFragmentDirections
                    .actionDaftarCatatanFragmentToDetailCatatanFragment("Isi catatan 2")
                findNavController().navigate(action)
            }
        }

        return view
    }
}
