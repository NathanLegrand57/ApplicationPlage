package com.example.applicationplage.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.applicationplage.R
import com.example.applicationplage.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private val TAG="DetailsActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mapButton.setOnClickListener {
            Toast.makeText(this,
                getString(R.string.aller_vers_la_carte_des_plages),
                Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Aller vers les cartes")

            finish()
        }

        binding.beachButton.setOnClickListener {
            Toast.makeText(this,
                getString(R.string.aller_vers_plages),
                Toast.LENGTH_SHORT).show()


            finish()
        }

    }
}