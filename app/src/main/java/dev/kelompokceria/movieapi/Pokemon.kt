package dev.kelompokceria.movieapi

import android.os.Parcelable
import java.io.Serializable

data class Pokemon(
    val id: Int,
    val abilities: List<String>,
    val evolutions: List<String>,
    val hitpoints: Int,
    val image_url: String,
    val location: String,
    val pokemon: String,
    val type: String
) : Serializable