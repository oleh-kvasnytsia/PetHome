package ua.olehkv.pethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ua.olehkv.pethome.databinding.AllDoneLayoutBinding

class AllDoneActivity : AppCompatActivity() {
    private lateinit var binding: AllDoneLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AllDoneLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btGoLogin.setOnClickListener {
            startActivity(Intent(this,LogInActivity::class.java))
        }
    }
}