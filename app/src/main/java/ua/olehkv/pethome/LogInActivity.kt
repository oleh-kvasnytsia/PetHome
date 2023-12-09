package ua.olehkv.pethome

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.olehkv.pethome.databinding.LogInLayoutBinding
import ua.olehkv.pethome.retrofit.entities.LogInRequestBody
import ua.olehkv.pethome.retrofit.MainApi

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: LogInLayoutBinding
    private lateinit var mainApi: MainApi
    private val baseUrl = "https://pethomeback.onrender.com"
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LogInLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("main", Context.MODE_PRIVATE)



        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY


        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()


        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mainApi = retrofit.create(MainApi::class.java)

        binding.apply {
            btLogIn.setOnClickListener {
                val email = edEmail.text.toString()
                val password = edPassword.text.toString()
                val logInRequestBody = LogInRequestBody(email, password)
                CoroutineScope(Dispatchers.IO).launch {
                    val resp = mainApi.logInUser(logInRequestBody).body()!!
                    runOnUiThread {
                        if (resp.status == "SUCCESS") {
                            startActivity(Intent(this@LogInActivity, MainActivity::class.java))
                            val editor = prefs.edit()
                            editor.putBoolean("auth", true).apply()
                        }

                        else {
//                            Toast.makeText(
//                                this@LogInActivity,
//                                "Status: ${resp.status}\nMessage: ${resp.message}",
//                                Toast.LENGTH_SHORT
//                            ).show()
                            Toast.makeText(
                                this@LogInActivity,
                                resp.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
        }
    }


}