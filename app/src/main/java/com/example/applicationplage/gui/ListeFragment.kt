package com.example.applicationplage.gui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationplage.R
import com.example.applicationplage.databinding.FragmentListeBinding
import com.example.applicationplage.domain.Plage
import com.example.applicationplage.presentation.PlageViewModel

class ListeFragment : Fragment() {
    private val viewModel : PlageViewModel by activityViewModels()

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

        val favoris = mutableListOf(false, false)
        val adapter = PlageRecyclerAdapter (viewModel.plages.value ?: listOf<Plage>(), favoris)

        binding.listePlages.adapter = adapter
        binding.listePlages.layoutManager = LinearLayoutManager(requireContext())
    }
}