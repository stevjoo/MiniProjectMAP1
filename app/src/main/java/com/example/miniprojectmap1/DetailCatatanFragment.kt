package com.example.miniprojectmap1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailCatatanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_catatan, container, false)

        val tvDetail = view.findViewById<TextView>(R.id.tvDetailCatatan)

        // Aman jika fragment statis dibuat tanpa argument
        val args = arguments?.let { DetailCatatanFragmentArgs.fromBundle(it) }
        tvDetail.text = args?.isiCatatan ?: "Tidak ada catatan dipilih"

        return view
    }

    companion object {
        fun newInstance(defaultText: String) = DetailCatatanFragment().apply {
            arguments = Bundle().apply { putString("isiCatatan", defaultText) }
        }
    }
}
