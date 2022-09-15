package com.plenart.newfilmofil.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.plenart.newfilmofil.R
import com.plenart.newfilmofil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}