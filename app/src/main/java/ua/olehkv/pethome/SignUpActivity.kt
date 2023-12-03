package ua.olehkv.pethome

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
import ua.olehkv.pethome.databinding.SignUpLayoutBinding
import ua.olehkv.pethome.retrofit.MainApi
import ua.olehkv.pethome.retrofit.entities.LogInRequestBody
import ua.olehkv.pethome.retrofit.entities.SignUpRequestBody

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: SignUpLayoutBinding
    private lateinit var mainApi: MainApi
    private val baseUrl = "https://pethomeback.onrender.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            btSignUp.setOnClickListener {
                val firstName = edFirstName.text.toString()
                val lastName = edLastName.text.toString()
                val email = edEmail.text.toString()
                val password = edCreatePassword.text.toString()
                val confirmedPassword = edConfirmPassword.text.toString()
                val signUpRequestBody = SignUpRequestBody(
                    confpass = "",
                    email = email,
                    firstName = firstName,
                    lastName = lastName,
                    password = password
                )
                CoroutineScope(Dispatchers.IO).launch {
                    val resp = mainApi.signUpUser(signUpRequestBody).body()!!
                    runOnUiThread {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Status: ${resp.status}\nMessage: ${resp.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }
    }
}