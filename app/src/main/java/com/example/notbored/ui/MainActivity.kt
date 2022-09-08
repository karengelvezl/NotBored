package com.example.notbored.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notbored.R
import com.example.notbored.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sendActivityScreen()


    }

    private fun sendActivityScreen() {
        binding.btnStart.setOnClickListener {

            val participants = binding.numberParticipants
            val numberParticipants = participants.toString()
            val intent = Intent(this, ActivitiesScreen::class.java).apply {
                putExtra("numberParticipants", numberParticipants)
            }
            startActivity(intent)

        }
    }




    private fun sendActivitiesScreen() {


    }
/*
    private fun sendTermsAndConditions() {
        binding.txtTermsAndConditions.setOnClickListener {
            val intent = Intent(this, TermsAndConditionsActivity::class.java).apply {
            }
            startActivity(intent)
        }


    }

 */
}