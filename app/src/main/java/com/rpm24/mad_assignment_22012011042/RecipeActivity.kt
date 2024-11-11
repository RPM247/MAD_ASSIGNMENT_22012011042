package com.rpm24.mad_assignment_22012011042

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rpm24.mad_assignment_22012011042.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    var imgCrop=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.fullScreen.setOnClickListener {
              if(imgCrop){
                  binding.itemImage.scaleType= ImageView.ScaleType.FIT_CENTER
                  imgCrop=imgCrop
              }else{
                  binding.itemImage.scaleType= ImageView.ScaleType.CENTER_CROP
                  imgCrop=!imgCrop
              }
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}