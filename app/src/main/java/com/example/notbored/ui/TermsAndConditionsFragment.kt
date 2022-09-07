package com.example.notbored.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.notbored.R
import com.example.notbored.databinding.LayoutTermsAndConditionsBinding


class TermsAndConditionsFragment : Fragment(R.layout.layout_terms_and_conditions) {

    private lateinit var binding: LayoutTermsAndConditionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = LayoutTermsAndConditionsBinding.bind(view)

    }
}