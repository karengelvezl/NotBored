package com.example.notbored.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.notbored.R
import com.example.notbored.databinding.ActivitySuggestionScreenBinding
import com.example.notbored.ui.service.APIService
import com.example.notbored.ui.service.responseObjects.SuggestionResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuggestionScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionScreenBinding

    private var random: Boolean = false
    private var nameActivity: String? = null
    private var numberParticipants: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestion_screen)
        binding = ActivitySuggestionScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras

        bundle?.let {
            bundle.apply {
                //Intent with data
                random = intent.getBooleanExtra("random", false)
                nameActivity = intent.getStringExtra("nameActivity")
                numberParticipants = intent.getStringExtra("numberParticipants")
            }
        }
        makeRequest(random, nameActivity?.let { it } ?: "", numberParticipants.let { it } ?: "0")
        with(binding) {
            tryAnotherButton.setOnClickListener {
                makeRequest(random, nameActivity?.let { it } ?: "", numberParticipants.let { it } ?: "0")
            }

            toolbar.setOnClickListener {
                val participants = numberParticipants
                val numberParticipants = participants.toString()
                val intent = Intent(applicationContext, ActivitiesScreen::class.java).apply {
                    putExtra("numberParticipants", numberParticipants)
                }
                startActivity(intent)
            }
        }
    }

    private fun makeRequest(random: Boolean, activity: String, participants: String) {
        when (random) {
            false -> {
                findSuggestion(activity, participants)
            }
            true -> {
                findRandomSuggestion()
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        //inicializo retrofit para luego crear una instancia
        return Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun findRandomSuggestion() {
        CoroutineScope(Dispatchers.IO).launch {
            //esto crea la instancia de backend para usarlo
            val apiResponse: Response<SuggestionResponse> = getRetrofit()
                .create(APIService::class.java).getRandom("activity")

            //obtengo el cuerpo de la respuesta
            val suggestionResponse: SuggestionResponse? = apiResponse.body()

            runOnUiThread {
                if (apiResponse.isSuccessful) {
                    suggestionResponse?.let {
                        binding.suggestionText.text = suggestionResponse.activity
                        binding.activityType1.text = "Random"
                        binding.activityType2.text = suggestionResponse.type.sentenceCase()
                        binding.activityTypeImage.visibility = View.VISIBLE
                        binding.priceRangeText.text = getPrice(suggestionResponse.price)
                        binding.personAmountText.text = suggestionResponse.participants
                    }
                }
            }
        }
    }

    private fun findSuggestion(
        activity: String,
        numberOfParticipants: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            //esto crea la instancia de backend para usar
            var url: String = ""
            numberParticipants?.let {
                if (numberOfParticipants.isNotBlank()) {
                    url = "activity?type=$activity&participants=$numberOfParticipants"
                } else {
                    url = "activity?type=$activity"
                }
            }


            val apiResponse: Response<SuggestionResponse> = getRetrofit()
                .create(APIService::class.java).getRandom(url)
            //getImagesOfDogsByBreed("$query/images")

            //obtengo el cuerpo de la respuesta
            val suggestionResponse: SuggestionResponse? = apiResponse.body()

            runOnUiThread {
                if (apiResponse.isSuccessful) {
                    suggestionResponse?.activity?.let {
                        binding.suggestionText.text = suggestionResponse.activity
                        binding.activityType1.text = suggestionResponse.type.sentenceCase()
                        binding.activityType2.text = ""
                        binding.activityTypeImage.visibility = View.GONE
                        binding.priceRangeText.text = getPrice(suggestionResponse.price)
                        binding.personAmountText.text = suggestionResponse.participants
                    } ?: run {
                        suggestionResponse?.error?.let {
                            Toast.makeText(
                                applicationContext,
                                suggestionResponse.error,
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        }

                    }
                }
            }
        }

    }

    private fun getPrice(value: String): String {
        val price = value.toDouble()
        when {
            price == 0.0 -> return "Free"
            (price > 0.0 && price <= 0.3) -> return "Low"
            (price > 0.3 && price <= 0.6) -> return "Medium"
            else -> return "High"
        }
    }

    private fun String.sentenceCase(): String {
        return this.substring(0, 1).uppercase() + this.substring(1)
    }

}
