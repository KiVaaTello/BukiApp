package com.kivaatello.fragments2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kivaatello.fragments2.ModeloPersonas
import com.kivaatello.fragments2.PersonasViewHolder
import com.kivaatello.fragments2.R

class PersonasAdapter(private val personas: ArrayList<ModeloPersonas>, private val contexto: Context): RecyclerView.Adapter<PersonasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonasViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return PersonasViewHolder(layoutInflater.inflate(R.layout.item_personas, parent, false))
    }

    override fun onBindViewHolder(holder: PersonasViewHolder, position: Int) {
        val item = personas[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = personas.size

}