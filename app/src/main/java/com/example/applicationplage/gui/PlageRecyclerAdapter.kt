package com.example.applicationplage.gui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationplage.R
import com.example.applicationplage.databinding.PlageItemBinding
import com.example.applicationplage.domain.Plage

class PlageRecyclerAdapter(var lesPlages: List<Plage>, val favoris: MutableList<Boolean>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class PlageViewHolder(bd: PlageItemBinding) : RecyclerView.ViewHolder(bd.root) {
        val nom = bd.itemPlageNom
        val description = bd.itemPlageDescription
        val image = bd.itemPlageImage
        val estFavori = bd.itemFavori
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plage_item, parent, false)
        val bd = PlageItemBinding.bind(view)
        return PlageViewHolder(bd)
    }

    override fun getItemCount() = lesPlages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as PlageViewHolder
        holder.nom.text = lesPlages[position].nom
        holder.description.text = lesPlages[position].description
        holder.estFavori.setOnClickListener {
            favoris[position] = !favoris[position]
        }
        val it = holder.itemView.context.resources.getIdentifier(
            lesPlages[position].image,
            "drawable",
            holder.itemView.context.packageName
        )
        holder.image.setImageResource(it)

        // Ajouter un listener sur le clic
        holder.itemView.setOnClickListener {
            ListeFragmentDirections.actionListeFragmentToDetailsFragment(position)
                .also {
                    holder.itemView.findNavController().navigate(it)
                }
        }
    }
}