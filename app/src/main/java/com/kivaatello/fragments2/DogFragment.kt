package com.kivaatello.fragments2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kivaatello.fragments2.databinding.FragmentDogsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class DogFragment: Fragment(R.layout.fragment_dogs) {
    private lateinit var binding: FragmentDogsBinding
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogsBinding.inflate(inflater, container, false)
        searchByName("akita")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun initRecyclerView(context: Context){
        adapter = DogAdapter(dogImages, context)
        binding.rvDogs.layoutManager = LinearLayoutManager(context)
        binding.rvDogs.adapter = adapter
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val call: Response<DogsResponse> =
                    getRetrofit().create(APIServiceDogs::class.java).getDogsByBreeds("$query/images")
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