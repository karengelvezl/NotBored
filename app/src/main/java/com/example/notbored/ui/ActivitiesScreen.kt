package com.example.notbored.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notbored.R
import com.example.notbored.databinding.ActivityActivitiesScreenBinding

class ActivitiesScreen : AppCompatActivity() {

    private lateinit var binding: ActivityActivitiesScreenBinding
    private lateinit var numberParticipants: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activities = resources.getStringArray(R.array.activities).toList()
        binding.lvActivities.adapter = ActivitiesAdapter(this, activities)
        numberParticipants = ""
        val bundle: Bundle? = intent.extras

        bundle?.let {
            bundle.apply {
                //Intent with data
                numberParticipants = getString("numberParticipants").toString()
            }
        }
        numberParticipants
        val intent2 = Intent(this, SuggestionScreen::class.java)
        with(binding) {
            lvActivities.setOnItemClickListener { adapterView, view, i, l ->
                val typeActivity = adapterView.getItemAtPosition(i) as String
                //llamada a la API con el putInt y el tipo de actividad numberParticipants
                intent2.putExtra("numberParticipants", numberParticipants)
                intent2.putExtra("nameActivity", typeActivity.lowercase())
                intent2.putExtra("random", false)
                startActivity(intent2)
            }
            shuffle.setOnClickListener {
                //llamar al metodo random de la api
                intent2.putExtra("random", true)
                startActivity(intent2)
            }
        }
    }

}