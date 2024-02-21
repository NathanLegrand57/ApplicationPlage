package com.example.applicationplage.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applicationplage.R

class NathanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nathan)

        supportFragmentManager.beginTransaction()
            .replace(R.id.plages_fragment_container, ListeFragment())
            .commit()
    }
}