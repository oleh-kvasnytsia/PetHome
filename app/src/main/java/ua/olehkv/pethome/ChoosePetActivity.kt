package ua.olehkv.pethome

import android.annotation.SuppressLint
import android.icu.lang.UCharacter.getAge
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.olehkv.pethome.adapters.SliderAdapter
import ua.olehkv.pethome.databinding.ActivityChoosePetBinding
import ua.olehkv.pethome.models.SliderData
import ua.olehkv.pethome.retrofit.MainApi
import ua.olehkv.pethome.retrofit.entities.PetInfo


class ChoosePetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoosePetBinding
    private lateinit var mainApi: MainApi

    private val url1 = "https://i.postimg.cc/qqQH1mD9/dog-photo1.jpg"
    private val url2 = "https://i.postimg.cc/mrpBkY0h/chernaya-nemeckaya-ovcharka-1.jpg"
    private val url3 = "https://i.postimg.cc/MKxPCGDX/1641459600-1-sobakovod-club-p-sobaki-vostochnaya-borzaya-1.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoosePetBinding.inflate(layoutInflater)


        init()
        setContentView(binding.root)
//        initSlider()
    }

    private fun init(){
        val baseUrl = "https://pethomeback.onrender.com"
        val petId = intent.getIntExtra("ID", 1)
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
                val petInfoResponse = mainApi.findAnimal(petId)
                if (petInfoResponse.isSuccessful){
                    val petInfo = petInfoResponse.body()!!
                    Log.d("MyTag", "petInfo: $petInfo")
                    runOnUiThread {
                        fillCard(petInfo)
                    }
                }
            }
            catch (e: Exception){
                runOnUiThread {
                    Toast.makeText(this@ChoosePetActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }



        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillCard(petInfo: PetInfo) = with(binding){

         fun getGender(g: String?) =
             when(g){
                 "M" -> "Male"
                 "F" -> "Female"
                 else -> "None"
             }

        fun getSize(size: String?) =
            if (size.isNullOrBlank()) "Not specified size"
            else size

        fun getNeuteredStatus(s: Int?) =
            when(s){
                1 -> "Sterelized"
                0 -> "Not Sterelized"
                else -> "No sterelization info"
            }
        fun getChippedStatus(s: Int?) =
            when(s){
                1 -> "Chipped"
                0 -> "Not chipped"
                else -> "No chipped info"
            }
        fun getAge(age: Int?) =
            when(age){
                1 -> "$age year"
                else-> "$age years"
            }

        fun getHouseTrained(t: Int?) =
            when(t){
                1 -> "House trained"
                else -> "Not house trained"
            }

        fun getDomesticatedStatus(d: Int?)=
            when(d) {
                1 -> "Domesticated"
                0 -> "Not domesticated"
                else -> "Not specified domestication"
            }



        val sep = "  \uD83D\uDC3E  "
        tvName.text = petInfo.basicInfo.name
        tvLocation.text = petInfo.basicInfo.city
        tvAbout.text = "About ${petInfo.basicInfo.name}"
        tvDesc.text = petInfo.basicInfo.description
        tvWantToAdopt.text = "Want to adopt \n${petInfo.basicInfo.name}?"
        initSlider(petInfo.photos)
        petInfo.apply{
            when {
                dogAttributes != null -> {
                    val attrs = petInfo.dogAttributes!!
                    tvBreed.text = attrs.breedName
                    tvMainInfo.text =
                            getGender(petInfo.basicInfo.gender) +
                                sep +
                            getSize(attrs.size) +
                                sep +
                            getAge(petInfo.basicInfo.age) +
                                sep +
                            getNeuteredStatus(attrs.isNeutered) +
                                sep +
                            getChippedStatus(attrs.isMicrochipped) +
                                sep+
                            getHouseTrained(attrs.isHouseTrained)

                }

                catAttributes != null -> {
                    val attrs = petInfo.catAttributes!!
                    tvBreed.text = attrs.breedName
                    tvMainInfo.text =
                                getGender(petInfo.basicInfo.gender) +
                                sep +
                                getSize(attrs.size) +
                                sep +
                                getAge(petInfo.basicInfo.age) +
                                sep +
                                getNeuteredStatus(attrs.isNeutered) +
                                sep +
                                getHouseTrained(attrs.isHouseTrained)

                }

                rodentAttributes != null -> {
                    val attrs = petInfo.rodentAttributes!!
                    tvBreed.text = attrs.typeName
                    tvMainInfo.text =
                                getGender(petInfo.basicInfo.gender) +
                                sep +
                                getSize(attrs.size) +
                                sep +
                                getAge(petInfo.basicInfo.age) +
                                sep +
                                getDomesticatedStatus(attrs.isDomesticated)
                }

                petInfo.fishAttributes != null -> {
                    val attrs = petInfo.fishAttributes!!
                    tvBreed.text = attrs.typeName
                    tvMainInfo.text =
                                getGender(petInfo.basicInfo.gender) +
                                sep +
                                getSize(attrs.size) +
                                sep +
                                getAge(petInfo.basicInfo.age)
                }
            }

        }
    }

    private fun initSlider(photos: ArrayList<String>){
        val sliderDataArrayList = photos.map { SliderData(it) }

//        val sliderDataArrayList = arrayListOf(
//            SliderData(url1),
//            SliderData(url2),
//            SliderData(url3),
//        )
        val sliderView = binding.slider
        val adapter = SliderAdapter(this, sliderDataArrayList)
        sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        sliderView.setSliderAdapter(adapter)
        sliderView.setInfiniteAdapterEnabled(false)
        sliderView.scrollTimeInSec = 3
//        sliderView.isAutoCycle = true
//        sliderView.startAutoCycle()
    }
}