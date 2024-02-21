package com.example.applicationplage.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.applicationplage.R
import com.example.applicationplage.databinding.ActivityNathanBinding

class NathanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNathanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNathanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.beachButton.setOnClickListener {
            findNavController(R.id.nav_host_fragment).navigate(R.id.listeFragment)
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

        override fun onSupportNavigateUp(): Boolean {
            return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
                    || super.onSupportNavigateUp()
    }
}