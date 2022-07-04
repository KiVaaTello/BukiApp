package com.kivaatello.fragments2.apiServices

import com.kivaatello.fragments2.ModeloPersonas
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIServicePersonas {
    @GET
    suspend fun getPersonas(@Url url: String): Response<List<ModeloPersonas>>
}