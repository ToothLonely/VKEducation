package dev.toothlonely.vkeducation

import kotlinx.serialization.Serializable

@Serializable
data class AppDetails(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val description: String,
    val category: String,
)
