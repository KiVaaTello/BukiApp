package com.kivaatello.fragments2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kivaatello.fragments2.ModeloProfesiones
import com.kivaatello.fragments2.ProfesionesViewHolder
import com.kivaatello.fragments2.R

class ProfesionesAdapter(private val profesiones: ArrayList<ModeloProfesiones>, private val contexto: Context): RecyclerView.Adapter<ProfesionesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesionesViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return ProfesionesViewHolder(layoutInflater.inflate(R.layout.item_profesiones, parent, false))
    }

    override fun onBindViewHolder(holder: ProfesionesViewHolder, position: Int) {
        val item = profesiones[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = profesiones.size

}