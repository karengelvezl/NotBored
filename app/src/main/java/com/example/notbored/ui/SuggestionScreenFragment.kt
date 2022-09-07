package com.example.notbored.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.example.notbored.R
import com.example.notbored.databinding.LayoutSuggestionScreenBinding


class SuggestionScreenFragment : Fragment(R.layout.layout_suggestion_screen) {

    private lateinit var binding: LayoutSuggestionScreenBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = LayoutSuggestionScreenBinding.bind(view)

        binding.tryAnotherButton.setOnClickListener{
            Toast.makeText(context, "Hello World", Toast.LENGTH_SHORT).show()
        }
    }
}