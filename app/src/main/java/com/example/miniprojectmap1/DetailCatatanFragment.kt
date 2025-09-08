package com.example.miniprojectmap1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class DetailCatatanFragment : Fragment() {

    private val args: DetailCatatanFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_catatan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvDetail = view.findViewById<TextView>(R.id.tvDetailCatatan)

        // Ambil data dengan Safe Args (fallback tetap ada)
        val isiCatatan = args.isiCatatan.ifEmpty {
            arguments?.getString("isiCatatan") ?: "Tidak ada catatan dipilih"
        }

        tvDetail.text = isiCatatan
    }
}
