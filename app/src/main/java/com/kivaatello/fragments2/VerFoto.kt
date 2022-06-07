package com.kivaatello.fragments2

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.kivaatello.fragments2.databinding.ActivityVerFotoBinding
import com.squareup.picasso.Picasso

class VerFoto: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityVerFotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent1: Intent? = intent
        val url: String = intent1!!.getStringExtra("url").toString()

        Picasso.get().load(url).into(binding.ivOneDog)
    }
}