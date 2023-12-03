package ua.olehkv.pethome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ua.olehkv.pethome.databinding.ActivityChoosePetBinding


class ChoosePetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoosePetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}