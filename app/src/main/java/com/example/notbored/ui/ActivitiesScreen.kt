package com.example.notbored.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notbored.R
import com.example.notbored.databinding.ActivityActivitiesScreenBinding

class ActivitiesScreen : AppCompatActivity() {

    private lateinit var  binding: ActivityActivitiesScreenBinding
    private lateinit var adapter: ActivitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activities = resources.getStringArray(R.array.activities).toList()
        binding.lvActivities.adapter = ActivitiesAdapter(this, activities)

        val bundle: Bundle? = intent.extras

        binding.shuffle.setOnClickListener {
            Toast.makeText(this, "RANDOM", Toast.LENGTH_LONG).show()
        }
    }

}