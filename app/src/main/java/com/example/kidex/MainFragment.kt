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
import com.example.kidex.model.PersonajeResponse
import com.example.kidex.network.RetrofitClient
import kotlinx.coroutines.launch

//Función para pasar variables por los fragments
fun <T : Fragment> T.withArguments(vararg pairs: Pair<String, Any?>): T {
    arguments = Bundle().apply {
        pairs.forEach { (key, value) ->
            when (value) {
                is Int -> putInt(key, value)
                is Double -> putDouble(key, value)
                is String -> putString(key, value)
            }
        }
    }
    return this
}

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPersonaje.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.getPersonajes()
                val personajes = response.items


                val adapter = PersonajeAdapter(personajes)
                binding.rvPersonaje.adapter = adapter

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()

                // Puedes mostrar un Toast o Snackbar aquí
            }
        }
    }

}