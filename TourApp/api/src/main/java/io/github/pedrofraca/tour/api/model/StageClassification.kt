package io.github.pedrofraca.tour.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StageClassification(
        var mountain: List<Classification>,
        var team: List<Classification>,
        var general: List<Classification>,
        var regularity: List<Classification>,
        var stage: List<Classification>)