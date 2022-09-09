package com.example.notbored.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

class SuggestionScreenActivity : AppCompatActivity() {

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
        receiveParameters(bundle)
        makeRequest(random, nameActivity ?: "", numberParticipants)
        onClick()
    }

    private fun onClick() {
        with(binding) {
            tryAnotherButton.setOnClickListener {
                makeRequest(random, nameActivity ?: "", numberParticipants)
            }
            toolbar.setOnClickListener {
                val intent = Intent(applicationContext, ActivitiesScreen::class.java).apply {
                    putExtra("numberParticipants", numberParticipants)
                }
                startActivity(intent)
            }
        }
    }

    //receive parameters from activityScreen
    private fun receiveParameters(bundle: Bundle?) {
        bundle?.let {
            bundle.apply {
                random = false
                nameActivity = null
                numberParticipants = null
                random = intent.getBooleanExtra("random", false)
                nameActivity = intent.getStringExtra("nameActivity")
                numberParticipants = intent.getStringExtra("numberParticipants")
            }
        }
    }

    //select function according to random is true or false
    private fun makeRequest(random: Boolean, activity: String, participants: String?) {
        when (random) {
            false -> {
                findSuggestion(activity, participants)
            }
            true -> {
                findRandomSuggestion(participants)
            }
        }
    }

    //Init Retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    var url = "activity"
    //Get random activity and binding parameters with the view
    private fun findRandomSuggestion(numberOfParticipants: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            // creation of the petition
            var url = "activity"

            numberParticipants?.let {
                    url = "activity?participants=$numberOfParticipants"
                }


            // creation of backend instance and send of query with no parameters
            val apiResponse: Response<SuggestionResponse> = getRetrofit()
                .create(APIService::class.java).getSuggestion(url)

            // get the answer of the query
            val suggestionResponse: SuggestionResponse? = apiResponse.body()

            // print the received info
            runOnUiThread {
                if (apiResponse.isSuccessful) {
                    suggestionResponse?.let {
                        binding.suggestionText.text = suggestionResponse.activity
                        binding.activityTypeTitle.text = "Random"
                        binding.activityType.text = suggestionResponse.type.sentenceCase()
                        binding.activityTypeImage.visibility = View.VISIBLE
                        binding.priceRangeText.text = getPrice(suggestionResponse.price)
                        binding.personAmountText.text = suggestionResponse.participants
                    }
                }
            }
        }
    }

    //Get specific activity and binding parameters with the view
    private fun findSuggestion(
        activity: String,
        numberOfParticipants: String?
    ) {
        CoroutineScope(Dispatchers.IO).launch {


            // creation of the petition
            var url = "activity?type=$activity"
            numberParticipants?.let {
                url = "activity?type=$activity&participants=$numberOfParticipants"

            }

            // creation of backend instance and send of query with participants and activityType as parameters
            val apiResponse: Response<SuggestionResponse> = getRetrofit()
                .create(APIService::class.java).getSuggestion(url)

            // get the answer of the query
            val suggestionResponse: SuggestionResponse? = apiResponse.body()

            // print the received info
            runOnUiThread {
                if (apiResponse.isSuccessful) {
                    suggestionResponse?.activity?.let {
                        binding.suggestionText.text = suggestionResponse.activity
                        binding.activityTypeTitle.text = suggestionResponse.type.sentenceCase()
                        binding.activityType.text = ""
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

    //Calculate according to the price range
    private fun getPrice(value: String): String {
        val price = value.toDouble()
        return when {
            price == 0.0 -> "Free"
            (price in 0.0..0.3) -> "Low"
            (price in 0.3 .. 0.6) -> "Medium"
            else -> "High"
        }
    }

    // this method applies formatting to the received text
    private fun String.sentenceCase(): String {
        return this.substring(0, 1).uppercase() + this.substring(1)
    }
}
