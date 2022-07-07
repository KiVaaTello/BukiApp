package com.kivaatello.fragments2.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kivaatello.fragments2.*
import com.kivaatello.fragments2.adapters.PersonasAdapter
import com.kivaatello.fragments2.apiServices.APIServicePersonas
import com.kivaatello.fragments2.databinding.FragmentPersonasBinding
import com.kivaatello.fragments2.models.ModeloPersonas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonasFragment : Fragment(R.layout.fragment_personas) {
    private lateinit var binding: FragmentPersonasBinding
    private lateinit var adapter: PersonasAdapter
    private var listaPersonas: ArrayList<ModeloPersonas> = ArrayList<ModeloPersonas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonasBinding.inflate(inflater, container, false)
        getDataFromApi("")
        return binding.root
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.100.122/KiVaa/JsonEncoder/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getDataFromApi(url2: String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val call: Response<List<ModeloPersonas>> =
                    getRetrofit().create(APIServicePersonas::class.java).getPersonas(url2)
                val personas: List<ModeloPersonas>? = call.body()
                if (call.isSuccessful) {
                    listaPersonas.clear()
                    if (personas != null) {
                        for (i in 0..personas.size - 1) {
                            listaPersonas.add(personas[i])
                        }

                        if (listaPersonas.size > 0) {
                            ThreadUtil.runOnUiThread {
                                initRecyclerView(requireContext())
                            }
                        }
                    }
                }
            }
        } catch (error: Exception) {
            print(error.message)
        }

    }

    private fun initRecyclerView(context: Context) {
        adapter = PersonasAdapter(listaPersonas, context)
        binding.rvPersonas.layoutManager = LinearLayoutManager(context)
        binding.rvPersonas.adapter = adapter
    }
}