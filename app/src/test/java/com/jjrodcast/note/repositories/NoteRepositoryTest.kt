package com.jjrodcast.note.repositories

import com.jjrodcast.data.datasources.NoteDataSource
import com.jjrodcast.data.repositories.NoteDataRepository
import com.jjrodcast.domain.repositories.NoteRepository
import com.jjrodcast.note.base.BaseTest
import com.jjrodcast.note.utils.TestUtils
import com.jjrodcast.note.utils.ZERO
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.any
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NoteRepositoryTest : BaseTest() {

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
    fun `Test For Insert Note`() = testDispatcher.runBlockingTest {
        repository.insertNote(any())
        coVerify { noteDataSource.insertNote(any()) }
    }

    @Test
    fun `Test For Delete Note`() = testDispatcher.runBlockingTest {
        repository.deleteNote(any())
        coVerify { noteDataSource.deleteNote(any()) }
    }

    @Test
    fun `Test For Update Note`() = testDispatcher.runBlockingTest {
        repository.updateNote(any())
        coVerify { noteDataSource.updateNote(any()) }
    }

    @Test
    fun `Test For Get Notes`() = testDispatcher.runBlockingTest {
        val notes = repository.getNotes().toList().flatten()
        notes shouldHaveSize TestUtils.notes.size
        every { noteDataSource.getNotes() }
    }

    @Test
    fun `Test For Get Note By Id`() = testDispatcher.runBlockingTest {
        val noteId = repository.getNoteById(any()).take(1).toList().firstOrNull()?.id ?: ZERO

        noteId shouldBeEqualTo TestUtils.note.id

        every { noteDataSource.getNoteById(any()) }

    }
}