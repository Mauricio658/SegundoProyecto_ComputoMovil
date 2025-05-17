package com.example.kidex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kidex.databinding.FragmentMainBinding
import com.example.kidex.model.Personaje
import com.example.kidex.network.RetrofitClient
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPersonaje.layoutManager = LinearLayoutManager(requireContext())

        Glide.with(this)
            .asGif()
            .load(R.drawable.loading) // nombre del gif: loading.gif
            .into(binding.loadingGif)

        lifecycleScope.launch {
            try {
                // Mostrar pantalla de carga
                binding.loadingLayout.visibility = View.VISIBLE
                binding.rvPersonaje.visibility = View.GONE

                val response = RetrofitClient.api.getPersonajes()
                val personajes = response.items

                val adapter = PersonajeAdapter(personajes) { personaje ->
                    val fragment = VistaDetallada().apply {
                        arguments = Bundle().apply {
                            putInt("id", personaje.id)
                        }
                    }
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fcvMain, fragment)
                        .addToBackStack(null)
                        .commit()
                }

                binding.rvPersonaje.adapter = adapter

                // Esperar 5 segundos antes de mostrar el RecyclerView
                kotlinx.coroutines.delay(5000)

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                // Ocultar pantalla de carga
                binding.loadingLayout.visibility = View.GONE
                binding.rvPersonaje.visibility = View.VISIBLE
            }
        }
    }

}
