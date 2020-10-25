package com.jjrodcast.note.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jjrodcast.data.datasources.NoteDataSource
import com.jjrodcast.data.repositories.NoteDataRepository
import com.jjrodcast.domain.repositories.NoteRepository
import com.jjrodcast.note.base.BaseTest
import com.jjrodcast.note.utils.TestUtils
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.any
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NoteRepositoryTest : BaseTest() {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val noteDataSource = mockk<NoteDataSource>(relaxed = true)
    private lateinit var repository: NoteRepository

    @Before
    override fun setup() {
        super.setup()
        repository = NoteDataRepository(noteDataSource)

        coEvery { noteDataSource.insertNote(any()) } just Runs
        coEvery { noteDataSource.deleteNote(any()) } just Runs
        coEvery { noteDataSource.updateNote(any()) } just Runs

        every { noteDataSource.getNoteById(any()) } returns flowOf(TestUtils.note)
        every { noteDataSource.getNotes() } returns flowOf(TestUtils.notes)
    }

    @Test
    fun `Test For Insert Note`() = runBlockingTest {
        repository.insertNote(any())
        coVerify { noteDataSource.insertNote(any()) }
    }

    @Test
    fun `Test For Delete Note`() = runBlockingTest {
        repository.deleteNote(any())
        coVerify { noteDataSource.deleteNote(any()) }
    }

    @Test
    fun `Test For Update Note`() = runBlockingTest {
        repository.updateNote(any())
        coVerify { noteDataSource.updateNote(any()) }
    }

    @Test
    fun `Test For Get Notes`() {
        repository.getNotes()
        every { noteDataSource.getNotes() }
    }

    @Test
    fun `Test For Get Note By Id`() {
        repository.getNoteById(any())
        every { noteDataSource.getNoteById(any()) }
    }
}