package com.example.notbored.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.notbored.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sendActivityScreen()
        sendTermsAndConditions()
    }


    //send parameters and start ActivityScreen
    private fun sendActivityScreen() {
        binding.btnStart.setOnClickListener {
            val participants = binding.numberParticipants.text

            if(TextUtils.isEmpty(participants)){

                val intent = Intent(this, ActivitiesScreen::class.java)
                startActivity(intent)
            } else {
                val intParticipants = Integer.parseInt(participants.toString())
                if(intParticipants in 1..8) {
                    val numberParticipants = participants.toString()
                    val intent = Intent(this, ActivitiesScreen::class.java).apply {
                        putExtra("numberParticipants", numberParticipants)
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"Invalid amount, try again",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    //start terms and conditions
    private fun sendTermsAndConditions() {
        binding.txtTermsAndConditions.setOnClickListener {
            val intent = Intent(this, TermsAndConditionsActivity::class.java).apply {
            }
            startActivity(intent)
        }
    }
}