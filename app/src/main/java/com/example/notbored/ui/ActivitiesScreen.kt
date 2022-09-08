package com.example.notbored.ui

import android.content.Intent
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
        var numberParticipants: String = ""
        val bundle: Bundle? = intent.extras

        bundle?.let {
            bundle.apply {
                //Intent with data
                numberParticipants = getString("numberParticipants").toString()
            }
        }

        val intent = Intent(this, SuggestionScreen::class.java)
        with(binding){
            lvActivities.setOnItemClickListener { adapterView, view, i, l ->
                val typeActivity = adapterView.getItemAtPosition(i) as String
                //llamada a la API con el putInt y el tipo de actividad numberParticipants -> true
                intent.putExtra("numberParticipants", numberParticipants)
                intent.putExtra("nameActivity", typeActivity.lowercase())
                intent.putExtra("random", false)
                startActivity(intent)
            }
            shuffle.setOnClickListener {
                //llamar al metodo random de la api -> false
                intent.putExtra("random", true)
                startActivity(intent)
            }
        }
    }

}