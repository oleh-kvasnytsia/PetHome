package ua.olehkv.pethome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.olehkv.pethome.adapters.GridSpacingItemDecoration
import ua.olehkv.pethome.adapters.PetRecyclerAdapter
import ua.olehkv.pethome.databinding.ActivityMainBinding
import ua.olehkv.pethome.models.PetModel
import ua.olehkv.pethome.retrofit.MainApi


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainApi: MainApi
    private lateinit var adapter: PetRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()



        initFilterListeners()


    }

    private fun init() = with(binding) {
        adapter = PetRecyclerAdapter(listOf(), this@MainActivity)
        rcView.adapter = adapter
        val spanCount = 2 // кількість елементів у рядку
        val spacing = 8
        val includeEdge = true
        rcView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
//        rcView.layoutManager = GridLayoutManager(this@MainActivity, 2)
        val baseUrl = "https://pethomeback.onrender.com"

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

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val petList = arrayListOf<PetModel>()
                for (i in 0..3) {
                    val resp: Response<List<PetModel>> = mainApi.getPetCardsInfo("dog", i, 4)
                    resp.body()?.let { it1 -> petList.addAll(it1) }
                }
                runOnUiThread {
                    adapter.updateList(petList)
                }

                Log.d("MyTag", petList.toString())
            }
            catch (e: Exception){
                runOnUiThread {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
        btSignUp.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
        }
        btLogIn.setOnClickListener {
            startActivity(Intent(this@MainActivity, LogInActivity::class.java))
        }

    }

    private fun initFilterListeners() = with(binding){
        imDogFilter.setOnClickListener {
            toggleFilterImages(it)
            showFilteredPets("dog")
        }
        imCatFilter.setOnClickListener {
            toggleFilterImages(it)
            showFilteredPets("cat")
        }
        imRabbitFilter.setOnClickListener {
            toggleFilterImages(it)
            showFilteredPets("rabbit")
        }
        imTurtleFilter.setOnClickListener {
            toggleFilterImages(it)
            showFilteredPets("turtle")
        }
    }

    private fun showFilteredPets(selectedPet: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val petList = arrayListOf<PetModel>()
                for (i in 0..3) {
                    val resp: Response<List<PetModel>> = mainApi.getPetCardsInfo(selectedPet, i, 4)
                    resp.body()?.let { it1 -> petList.addAll(it1) }
                }
                runOnUiThread { adapter.updateList(petList) }

                Log.d("MyTag", petList.toString())
            }
            catch (e: Exception){
                runOnUiThread {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun toggleFilterImages(view: View){
        val filterViews = binding.run {
            listOf(imDogFilter, imCatFilter, imRabbitFilter, imTurtleFilter)
        }
        for (v in filterViews)
            if (v == view) v.setBackgroundResource(R.drawable.button_bg2)
            else v.background = null

    }
}