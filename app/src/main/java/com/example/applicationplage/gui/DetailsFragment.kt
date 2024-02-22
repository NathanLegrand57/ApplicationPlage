package com.example.applicationplage.gui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.applicationplage.R
import com.example.applicationplage.databinding.FragmentDetailsBinding
import com.example.applicationplage.databinding.FragmentListeBinding
import com.example.applicationplage.presentation.PlageViewModel
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.ScaleBarOverlay

class DetailsFragment : Fragment() {
    private val viewModel : PlageViewModel by activityViewModels()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.plages.value?.let {
            val plage = it[plageId]
            binding.TitrePlage.text = plage.nom
            binding.DescriptionPlage.text = plage.description
            val it = resources.getIdentifier(
                plage.image,
                "drawable",
                requireContext().packageName
            )
            binding.largeurPlage.text = plage.largeur.toString()
            binding.longueurPlage.text = plage.longueur.toString()
            //binding.imagePlage.setImageResource(it)
            val startPoint =
                GeoPoint(plage.latitude, plage.longitude)
            binding.imageMapPlage.setUseDataConnection(true)
            binding.imageMapPlage.zoomController.setZoomInEnabled(true)
            binding.imageMapPlage.zoomController.setZoomOutEnabled(true)
            binding.imageMapPlage.setMultiTouchControls(true)
            binding.imageMapPlage.getController().setCenter(startPoint)
            binding.imageMapPlage.getController().setZoom(13.5)
            val myScaleBarOverlay = ScaleBarOverlay(binding.imageMapPlage)
            binding.imageMapPlage.getOverlays().add(myScaleBarOverlay)
            val startMarker = Marker(binding.imageMapPlage)
            startMarker.position = startPoint
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            binding.imageMapPlage.getOverlays().add(startMarker)
        }
    }
}