package com.example.applicationplage.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Plage(val nom: String, val description: String, val image: String, val largeur : Int, val longueur: Int, val latitude: Double, val longitude : Double, val url : String) : Parcelable {
}