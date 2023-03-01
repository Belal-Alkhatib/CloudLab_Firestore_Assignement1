package com.b.alkhatib.cloudlab_firebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.b.alkhatib.cloudlab_firebase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        binding.btnAddInfo.setOnClickListener {
            startActivity(Intent(this, AddInformationActivity::class.java))
        }
        binding.btnGetInfo.setOnClickListener {
            startActivity(Intent(this, GetInformationActivity::class.java))
        }
    }
}