package com.kivaatello.fragments2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kivaatello.fragments2.databinding.ItemProfesionesBinding

class ProfesionesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemProfesionesBinding.bind(view)

    fun bind(profesion: ModeloProfesiones){
        binding.tvProfesion.text = "Profesion: ${profesion.profesion}"
        binding.tvIdProfesion.text = "Id: ${profesion.idProfesion}"
    }
}