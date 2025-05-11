package com.example.kidex

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidex.databinding.PersonajeElementBinding
import com.example.kidex.model.Personaje

class PersonajeViewHolder(
    private val binding: PersonajeElementBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(personaje: Personaje) {
        binding.tvNombre.text = personaje.name
        binding.tvAFI.text = personaje.affiliation ?: "Sin afiliación"

        Glide.with(binding.ivPersonaje.context)
            .load(personaje.image)
            .into(binding.ivPersonaje)
    }
}
