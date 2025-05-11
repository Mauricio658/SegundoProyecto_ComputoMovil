package com.example.kidex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidex.databinding.PersonajeElementBinding
import com.example.kidex.model.Personaje

class PersonajeAdapter(
    private val personajes: List<Personaje>
) : RecyclerView.Adapter<PersonajeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val binding = PersonajeElementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonajeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        holder.bind(personajes[position])
    }

    override fun getItemCount(): Int = personajes.size
}
