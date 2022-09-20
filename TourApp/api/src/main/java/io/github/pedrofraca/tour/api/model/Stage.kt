package io.github.pedrofraca.tour.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stage(
        val name: String,
        val winner: String? = null,
        val leader: String? = null,
        val images: List<String>? = null,
        val description: String? = null,
        val km: String? = null,
        val imgUrl: String? = null,
        val date: String? = null,
        val stage: String,
        val averageSpeed: String? = null,
        val startFinish: String? = null) {
}