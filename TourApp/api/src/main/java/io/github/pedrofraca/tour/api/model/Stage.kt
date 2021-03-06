package io.github.pedrofraca.tour.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Stage(
        val name: String,
        @Json(name = "stage-winner")
        val winner: String? = null,
        @Json(name = "stage-leader")
        val leader: String? = null,
        @Json(name = "stage-images")
        val images: List<String>? = null,
        val description: String? = null,
        val km: String? = null,
        val imgUrl: String? = null,
        val date: String? = null,
        val stage: String,
        @Json(name = "avg-speed")
        val averageSpeed: String? = null,
        @Json(name = "start-finish")
        val startFinish: String? = null) {
}