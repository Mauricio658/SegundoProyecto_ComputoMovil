package com.example.kidex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.kidex.databinding.FragmentVistaDetalladaBinding
import com.example.kidex.network.RetrofitClient
import kotlinx.coroutines.launch

class VistaDetallada : Fragment() {
    private var _binding: FragmentVistaDetalladaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVistaDetalladaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personajeId = arguments?.getInt("id") ?: return

        lifecycleScope.launch {
            try {
                val personaje = RetrofitClient.api.getPersonajeDetalle(personajeId)

                // Carga en la UI
                binding.tvNombre.text = personaje.name
                binding.tvKi.text = personaje.ki.toString()
                binding.tvKiMax.text = personaje.maxKi.toString()
                binding.tvRaza.text = personaje.race
                binding.tvGenero.text = personaje.gender
                binding.tvDescripcion.text = personaje.description
                binding.tvAfiliacion.text = personaje.affiliation

                Glide.with(requireContext()).load(personaje.image).into(binding.ivPersonaje)

                val contenedor = binding.contenedorTransformaciones
                contenedor.removeAllViews()

                val listaTransformaciones = personaje.transformations

                if (!listaTransformaciones.isNullOrEmpty()) {
                    binding.tvTransformaciones.visibility = View.VISIBLE

                    listaTransformaciones.forEach { t ->
                        val itemView = layoutInflater.inflate(R.layout.item_transformaciones, contenedor, false)

                        val iv = itemView.findViewById<ImageView>(R.id.ivTransformacion)
                        val nombre = itemView.findViewById<TextView>(R.id.tvNombreTransformacion)
                        val ki = itemView.findViewById<TextView>(R.id.tvKiTransformacion)

                        nombre.text = t.name
                        "Ki: ${t.ki}".also { ki.text = it }
                        Glide.with(requireContext()).load(t.image).into(iv)

                        contenedor.addView(itemView)
                    }
                } else {
                    binding.tvTransformaciones.visibility = View.GONE
                }



            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
