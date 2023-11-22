package ua.olehkv.pethome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import ua.olehkv.pethome.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pets = List(30) {PetModel(it, "Bobik $it", "Lviv")}
        binding.rcView.adapter = PetRecyclerAdapter(pets)
        binding.rcView.layoutManager = GridLayoutManager(this, 2)
    }
}