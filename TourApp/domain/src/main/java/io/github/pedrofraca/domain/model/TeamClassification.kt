package io.github.pedrofraca.domain.model

data class TeamClassification (var time: String?,
                               var pos: String?,
                               var team: String?) {

    override fun toString(): String {
        return "$pos - $time $team"
    }
}