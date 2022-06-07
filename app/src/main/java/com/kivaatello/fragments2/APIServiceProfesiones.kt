package com.kivaatello.fragments2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIServiceProfesiones {
    @GET
    suspend fun getProfesiones(@Url url: String): Response<List<ModeloProfesiones>>
}