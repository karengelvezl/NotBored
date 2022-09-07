package com.example.notbored.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notbored.R
import com.example.notbored.databinding.FragmentActivitiesBinding

class ActivitiesScreen : Fragment() {
    private lateinit var binding: FragmentActivitiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentActivitiesBinding.bind(view)
        val activities = resources.getStringArray(R.array.activities).toList()
        binding.lvActivities.adapter = ActivitiesAdapter(view.context, activities)

    }
}
