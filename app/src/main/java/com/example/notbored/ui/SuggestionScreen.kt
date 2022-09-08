package com.example.notbored.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
    private var numberParticipants: Int? = null

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
                numberParticipants = intent.getIntExtra("numberParticipants", 0)
            }
        }
    }

        private fun getRetrofit(): Retrofit {
            //inicializo retrofit para luego crear una instancia
            return Retrofit.Builder()
                .baseUrl("http://www.boredapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun findRandomSuggestion() {
            CoroutineScope(Dispatchers.IO).launch {
                //esto crea la instancia de backend para usarlo
                val apiResponse: Response<SuggestionResponse> = getRetrofit()
                    .create(APIService::class.java).getRandom()
                //getImagesOfDogsByBreed("$query/images")

                //obtengo el cuerpo de la respuesta
                val suggestionResponse: SuggestionResponse? = apiResponse.body()

                runOnUiThread {
                    if (apiResponse.isSuccessful) {
                        binding.suggestionText.text = suggestionResponse?.activity
                        binding.activityType1.text = suggestionResponse?.type
                        binding.activityType2.text = suggestionResponse?.type
                        binding.activityTypeImage.visibility = View.VISIBLE
                        binding.priceRangeText.text = suggestionResponse?.price.toString()
                        binding.personAmountText.text = suggestionResponse?.participants.toString()
                    }
                }
            }
        }

        private fun findSuggestion(
            activity: String,
            numberOfParticipants: Int
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                //esto crea la instancia de backend para usarlo
                val apiResponse: Response<SuggestionResponse> = getRetrofit()
                    .create(APIService::class.java).getRandom()
                //getImagesOfDogsByBreed("$query/images")

                //obtengo el cuerpo de la respuesta
                val suggestionResponse: SuggestionResponse? = apiResponse.body()

                runOnUiThread {
                    if (apiResponse.isSuccessful) {
                        binding.suggestionText.text = suggestionResponse?.activity
                        binding.activityType1.text = suggestionResponse?.type
                        binding.activityType2.text = ""
                        binding.activityTypeImage.visibility = View.GONE
                        binding.priceRangeText.text = suggestionResponse?.price.toString()
                        binding.personAmountText.text = suggestionResponse?.participants.toString()
                    }
                }
            }
        }
    }
