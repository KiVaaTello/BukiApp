package com.kivaatello.fragments2

import com.google.gson.annotations.SerializedName

data class ModeloProfesiones (@SerializedName("nombre_profesion") var profesion: String, @SerializedName("id_profesion") var idProfesion: Int)