package com.kivaatello.fragments2.apiServices

import com.kivaatello.fragments2.models.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIServiceDogs {
    @GET
    suspend fun getDogsByBreeds(@Url url: String): Response<DogsResponse>
}