package com.example.applicationplage.gui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationplage.R
import com.example.applicationplage.databinding.FragmentListeBinding
import com.example.applicationplage.domain.Plage

class ListeFragment : Fragment() {
    private lateinit var binding : FragmentListeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_liste, container, false)
        binding = FragmentListeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initialisation des données
        // Création des plages
        val lesPlages = listOf(
            Plage("La Palmyre Les Mathes", "La Palmyre est un quartier résidentiel et touristique", "plage_de_la_palmyre"),
            Plage("Plage des Sables d'Olonne", "Les Sables d'Olonne est une ville située en Vendée", "plage_sables_d_olonne")
        )
        val favoris = mutableListOf(false, false)
        val adapter = PlageRecyclerAdapter (lesPlages, favoris)

        binding.listePlages.adapter = adapter
        binding.listePlages.layoutManager = LinearLayoutManager(requireContext())
    }
}