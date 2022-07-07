package com.kivaatello.fragments2.models

import com.google.gson.annotations.SerializedName

data class ModeloPersonas (@SerializedName("nombre") var nombre: String, @SerializedName("edad") var edad: Int, @SerializedName("pais") var pais: String)