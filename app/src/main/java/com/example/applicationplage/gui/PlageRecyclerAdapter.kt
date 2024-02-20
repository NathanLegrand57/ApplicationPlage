package com.example.applicationplage.gui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationplage.R
import com.example.applicationplage.databinding.PlageItemBinding
import com.example.applicationplage.domain.Plage

class PlageRecyclerAdapter(val lesPlages: List<Plage>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class PlageViewHolder (bd : PlageItemBinding) : RecyclerView.ViewHolder(bd.root) {
        val nom = bd.itemPlageNom
        val description = bd.itemPlageDescription
        val image = bd.itemPlageImage
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
        val it = holder.itemView.context.resources.getIdentifier(
            lesPlages[position].image,
            "drawable",
            holder.itemView.context.packageName
        )
        holder.image.setImageResource(it)
    }
}