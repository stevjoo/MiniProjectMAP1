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

        btnNote1.setOnClickListener {
            val action = DaftarCatatanFragmentDirections.actionDaftarCatatanFragmentToDetailCatatanFragment("Isi catatan 1")
            findNavController().navigate(action)
        }

        btnNote2.setOnClickListener {
            val action = DaftarCatatanFragmentDirections.actionDaftarCatatanFragmentToDetailCatatanFragment("Isi catatan 2")
            findNavController().navigate(action)
        }

        return view
    }
}
