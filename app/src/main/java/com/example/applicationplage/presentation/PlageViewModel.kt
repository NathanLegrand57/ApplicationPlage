package com.example.applicationplage.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationplage.domain.Plage

class PlageViewModel : ViewModel() {
    private val _plages = MutableLiveData<List<Plage>>()

    val plages: LiveData<List<Plage>>
        get() = _plages


    fun setPlages(plages : List<Plage>) {
        _plages.value = plages
    }

    fun addPlage(plage: Plage) {
        val plages = _plages.value?.toMutableList() ?: mutableListOf()
        plages.add(plage)
        _plages.value = plages
    }

    init {
        _plages.value = listOf<Plage>(
            Plage("La Palmyre Les Mathes", "La Palmyre est un quartier résidentiel et touristique", "plage_de_la_palmyre", 15, 48, 45.6740933, -1.16671, "https://www.royanatlantique.fr/plages-charente-maritime/plage-de-la-palmyre-les-mathes/" ),
            Plage("Plage des Sables d'Olonne", "Les Sables d'Olonne est une ville située en Vendée", "plage_sables_d_olonne", 100, 546, 46.4932477, -1.7884719, "https://www.lessablesdolonne.com/")
        )
    }
}