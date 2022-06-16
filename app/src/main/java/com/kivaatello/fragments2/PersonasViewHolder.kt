package com.kivaatello.fragments2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kivaatello.fragments2.databinding.ItemPersonasBinding

class PersonasViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemPersonasBinding.bind(view)

    fun bind(persona: ModeloPersonas){
        binding.tvNombre.text = "Nombre: ${persona.nombre}"
        binding.tvEdad.text = "Edad: ${persona.edad}"
        binding.tvPais.text = "Pais: ${persona.pais}"
    }
}