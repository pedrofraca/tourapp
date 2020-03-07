package io.github.pedrofraca.domain.model

import java.util.*

/**
 * Created by pedrofraca on 15/07/15.
 */
data class StageClassification(
        var mountain: ArrayList<Classification>,
        var team: ArrayList<TeamClassification>,
        var general: ArrayList<Classification>,
        var regularity: ArrayList<Classification>,
        var stage: ArrayList<Classification>)