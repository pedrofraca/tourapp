package io.github.pedrofraca.tour.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamClassification (var time: String?,
                               var pos: String?,
                               var team: String?) {

    override fun toString(): String {
        return "$pos - $time $team"
    }
}