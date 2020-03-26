package io.github.pedrofraca.tour.api

import org.junit.Assert
import org.junit.Test
import java.io.IOException

class TourScrappingIntegration {
    @Throws(IOException::class)
    @Test
    fun stagesEndpoint() {
        val api = ServiceFactory().build(TourScrappingService::class.java)
        val stages = api.stages().blockingGet()
        Assert.assertFalse(stages?.isEmpty() ?: true)
    }

    @Throws(IOException::class)
    @Test
    fun classificationEndpoint() {
        val api = ServiceFactory().build(TourScrappingService::class.java)
        val stages = api.getClasificationForStage("1").blockingGet()
        Assert.assertFalse(stages.general.isEmpty())
    }
}