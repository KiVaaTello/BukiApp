package com.kivaatello.fragments2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kivaatello.fragments2.APIServiceDogs
import com.kivaatello.fragments2.DogsResponse
import com.kivaatello.fragments2.R
import com.kivaatello.fragments2.ThreadUtil
import com.kivaatello.fragments2.adapters.DogAdapter
import com.kivaatello.fragments2.databinding.FragmentDogsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.Exception

class DogFragment : Fragment(R.layout.fragment_dogs) {
    private lateinit var binding: FragmentDogsBinding
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()
    private lateinit var razas: Array<String>
    private lateinit var razasAdapter: ArrayAdapter<String>
    private lateinit var spinner: Spinner
    private lateinit var query: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogsBinding.inflate(inflater, container, false)
        spinner = binding.spinnerRazas
        razas = resources.getStringArray(R.array.razas)
        razasAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, razas)
        binding.spinnerRazas.adapter = razasAdapter


        spinner.onItemSelectedListener = object:
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                query = spinner.selectedItem.toString()
                searchByName(query.lowercase())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun initRecyclerView(context: Context) {
        adapter = DogAdapter(dogImages, context)
        binding.rvDogs.layoutManager = LinearLayoutManager(context)
        binding.rvDogs.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val call: Response<DogsResponse> =
                    getRetrofit().create(APIServiceDogs::class.java)
                        .getDogsByBreeds("$query/images")
                val puppies: DogsResponse? = call.body()
                if (call.isSuccessful) {
                    val images: List<String> = puppies?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    ThreadUtil.runOnUiThread { initRecyclerView(requireContext()) }
                }
            }
        } catch (error: Exception) {
            print(error.message)
        }
    }

}