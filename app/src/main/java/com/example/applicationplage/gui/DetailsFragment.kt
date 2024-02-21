package com.example.applicationplage.gui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.applicationplage.R
import com.example.applicationplage.databinding.FragmentDetailsBinding
import com.example.applicationplage.databinding.FragmentListeBinding

class DetailsFragment : Fragment() {
    private lateinit var binding : FragmentDetailsBinding
    private var plageId: Int = -1
    private val TAG = "Detail de la plage"
    val args : DetailsFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        plageId = args.plageId
        Log.d(TAG,"l'id de plage est $plageId")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_liste, container, false)
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}