package com.kivaatello.fragments2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kivaatello.fragments2.apiServices.APIServiceProfesiones
import com.kivaatello.fragments2.ModeloProfesiones
import com.kivaatello.fragments2.R
import com.kivaatello.fragments2.ThreadUtil
import com.kivaatello.fragments2.adapters.ProfesionesAdapter
import com.kivaatello.fragments2.databinding.FragmentProfesionesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.Exception

class ProfesionesFragment: Fragment(R.layout.fragment_profesiones) {
    private lateinit var binding: FragmentProfesionesBinding
    private lateinit var adapter: ProfesionesAdapter
    private var listaProfesiones: ArrayList<ModeloProfesiones> = ArrayList<ModeloProfesiones>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfesionesBinding.inflate(inflater, container, false)
        getDataFromApi("buscar-profesion")
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://sigo.work/bk/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getDataFromApi(url2: String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val call: Response<List<ModeloProfesiones>> =
                    getRetrofit().create(APIServiceProfesiones::class.java).getProfesiones(url2)
                val profession: List<ModeloProfesiones>? = call.body()
                if (call.isSuccessful) {
                    listaProfesiones.clear()
                    if (profession != null) {
                        for (i in 0..profession.size - 1) {
                            listaProfesiones.add(profession[i])
                        }

                        if (listaProfesiones.size > 0) {
                            ThreadUtil.runOnUiThread {
                                initRecyclerView(requireContext())
                            }
                        }
                    }
                }
            }
        } catch (error: Exception){
            print(error.message)
        }
    }

    private fun initRecyclerView(context: Context){
        adapter = ProfesionesAdapter(listaProfesiones, context)
        binding.rvProfesiones.layoutManager = LinearLayoutManager(context)
        binding.rvProfesiones.adapter = adapter
    }
}

