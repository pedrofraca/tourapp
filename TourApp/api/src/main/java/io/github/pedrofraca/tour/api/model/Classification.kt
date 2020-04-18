package io.github.pedrofraca.tour.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Classification(var time: String?,
                          var country: String?,
                          var team: String?,
                          var pos: String?,
                          var rider: String?)