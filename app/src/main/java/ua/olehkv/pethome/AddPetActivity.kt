package ua.olehkv.pethome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ua.olehkv.pethome.databinding.AddDogCatLayoutBinding

class AddPetActivity : AppCompatActivity() {
    private lateinit var binding: AddDogCatLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddDogCatLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}