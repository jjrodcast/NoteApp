package com.jjrodcast.note.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jjrodcast.domain.entities.Note
import com.jjrodcast.domain.usecases.NoteUseCase
import com.jjrodcast.note.base.BaseTest
import com.jjrodcast.note.features.home.viewmodel.ListNoteViewModel
import com.jjrodcast.note.utils.TestUtils
import com.jjrodcast.note.utils.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldHaveSize
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListNoteViewModelTes : BaseTest() {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val noteUseCase = mockk<NoteUseCase>(relaxed = true)
    private val observerNotes = mockk<Observer<List<Note>>>(relaxed = true)

    private lateinit var listNoteViewModel: ListNoteViewModel

    @Before
    override fun setup() {
        super.setup()
        every { noteUseCase.getNotes() } returns flowOf(TestUtils.notes)

        listNoteViewModel = ListNoteViewModel(noteUseCase)

        listNoteViewModel.notes.observeForever(observerNotes)
    }

    @Test
    fun `Test For Observe Changes`() = testDispatcher.runBlockingTest {
        val notes = listNoteViewModel.notes.getOrAwaitValue()
        notes shouldHaveSize TestUtils.notes.size
        verify { observerNotes.onChanged(any()) }
    }


}