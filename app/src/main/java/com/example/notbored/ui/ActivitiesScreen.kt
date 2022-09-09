package com.example.notbored.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notbored.R
import com.example.notbored.databinding.ActivityActivitiesScreenBinding

class ActivitiesScreen : AppCompatActivity() {

    private lateinit var binding: ActivityActivitiesScreenBinding
    private var numberParticipants: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activities = resources.getStringArray(R.array.activities).toList()
        binding.lvActivities.adapter = ActivitiesAdapter(this, activities)

        // we receive the data of amount of participants and activity in a Bundle
        val bundle: Bundle? = intent.extras

        bundle?.let {
            bundle.apply {
                //Intent with data
                numberParticipants.let { getString("numberParticipants").toString()}
            }
        }

        val intent2 = Intent(this, SuggestionScreenActivity::class.java)

        with(binding) {
            lvActivities.setOnItemClickListener { adapterView, view, i, l ->
                val typeActivity = adapterView.getItemAtPosition(i) as String

                // Here is were the data is sent to SuggestionScreenActivity, later this info is going
                // to be used to make the call to the API
                intent2.putExtra("numberParticipants", numberParticipants)
                intent2.putExtra("nameActivity", typeActivity.lowercase())
                intent2.putExtra("random", false)
                startActivity(intent2)
            }
            shuffle.setOnClickListener {
                // Here is were we send the "random flag", SuggestionScreenActivity is going to
                // take this info and make an empty call to the API which is going to respond with
                // a random activity, we consider if the user provided a participant amount number
                intent2.putExtra("numberParticipants", numberParticipants)
                intent2.putExtra("random", true)
                startActivity(intent2)
            }
        }
    }

}