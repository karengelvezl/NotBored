package com.example.notbored.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        bundle?.let {
            bundle.apply {
                //Intent with data
                val string: String? = getString("numberParticipants")

            }
        }


        with(binding){
            lvActivities.setOnItemClickListener { adapterView, view, i, l ->
                val typeActivity = adapterView.getItemAtPosition(i) as String
                //llamada a la API con el putInt y el tipo de actividad numberParticipants
            }
            shuffle.setOnClickListener {
                //llamar al metodo random de la api
            }
        }
    }
}