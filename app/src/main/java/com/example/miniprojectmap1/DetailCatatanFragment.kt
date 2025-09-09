package com.example.miniprojectmap1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

// Fragment ini buat nampilin detail isi catatan
class DetailCatatanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout buat fragment detail
        val view = inflater.inflate(R.layout.fragment_detail_catatan, container, false)

        val tvDetail = view.findViewById<TextView>(R.id.tvDetailCatatan)

        // Ambil argument dari Jetpack Navigation
        val args = arguments?.let { DetailCatatanFragmentArgs.fromBundle(it) }
        tvDetail.text = args?.isiCatatan ?: "Tidak ada catatan dipilih"

        return view
    }

    companion object {
        // Factory method buat dynamic fragment (tanpa Navigation Component)
        fun newInstance(defaultText: String) = DetailCatatanFragment().apply {
            arguments = Bundle().apply { putString("isiCatatan", defaultText) }
        }
    }
}
