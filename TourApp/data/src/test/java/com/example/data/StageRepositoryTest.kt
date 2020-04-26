package com.example.data

import io.github.pedrofraca.data.datasource.ReadOnlyDataSource
import io.github.pedrofraca.data.datasource.WriteDataSource
import io.github.pedrofraca.data.datasource.stage.StagesRepositoryImpl
import io.github.pedrofraca.domain.model.StageModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class StageRepositoryTest {

    private val db = mockk<WriteDataSource<StageModel>>(relaxed = true)
    private val api = mockk<ReadOnlyDataSource<StageModel>>(relaxed = true)
    private val repo = StagesRepositoryImpl(api, db)

    @Test
    fun `test empty response doesn't save anything`() {
        every { api.get() } returns emptyList()

        repo.refresh()

        verify { api.get() }
        verify(exactly = 0) { db.save(any()) }
    }

    @Test
    fun `test non empty response saves items`() {
        every { api.get() } returns listOf(StageModel("patata", stage = "1"))

        repo.refresh()

        verify { api.get() }
        verify(exactly = 1) { db.save(any()) }
    }
}