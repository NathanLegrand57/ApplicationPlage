package com.example.applicationplage.gui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.applicationplage.R
import com.example.applicationplage.databinding.FragmentCreationBinding
import com.example.applicationplage.domain.Plage

class CreationFragment : Fragment() {
    private lateinit var binding : FragmentCreationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.creationButton.setOnClickListener {
            val plage = Plage(binding.creationTitre.text.toString(),
                binding.creationDescription.text.toString(), binding.creationImage.text.toString(),
                binding.creationLargeur.text.toString().toInt(), binding.creationLongueur.text.toString().toInt(),
                binding.creationLatitude.text.toString().toDouble(), binding.creationLongitude.text.toString().toDouble(),
                binding.creationLien.text.toString())

            setFragmentResult("requestKey", bundleOf("plage" to plage))

            findNavController().navigateUp()
        }
    }
}
