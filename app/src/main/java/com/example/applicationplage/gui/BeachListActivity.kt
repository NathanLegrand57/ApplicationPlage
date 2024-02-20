package com.example.applicationplage.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationplage.R
import com.example.applicationplage.databinding.ActivityBeachListBinding
import com.example.applicationplage.databinding.PlageItemBinding
import com.example.applicationplage.domain.Plage

class BeachListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBeachListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeachListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lesPlages = listOf(
            Plage("La Palmyre Les Mathes", "La Palmyre est un quartier résidentiel et touristique", "plage_de_la_palmyre.jpg"),
            Plage("Plage des Sables d'Olonne", "Les Sables d'Olonne est unne ville située en Vendée", "plage_des_sables_d'olonne.jpg")
        )
        val adapter = PlageRecyclerAdapter (lesPlages)

        binding.listePlages.adapter = adapter
        binding.listePlages.layoutManager = LinearLayoutManager(this)
    }
}