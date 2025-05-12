package com.example.kidex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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


        lifecycleScope.launch {
            try {
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

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
