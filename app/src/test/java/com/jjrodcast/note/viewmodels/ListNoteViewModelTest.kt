package com.jjrodcast.note.viewmodels

import androidx.lifecycle.Observer
import com.jjrodcast.domain.entities.Note
import com.jjrodcast.domain.usecases.NoteUseCase
import com.jjrodcast.note.base.BaseTest
import com.jjrodcast.note.features.home.viewmodel.ListNoteViewModel
import com.jjrodcast.note.utils.TestUtils
import com.jjrodcast.note.utils.getOrAwaitValue
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldHaveSize
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ListNoteViewModelTes : BaseTest() {

    private val noteUseCase = mockk<NoteUseCase>(relaxed = true)
    private val observerNotes = mockk<Observer<List<Note>>>(relaxed = true)

    private lateinit var viewModel: ListNoteViewModel

    @Before
    override fun setup() {
        super.setup()
        every { noteUseCase.getNotes() } returns flowOf(TestUtils.notes)
        coEvery { noteUseCase.deleteNote(any()) } just Runs

        viewModel = ListNoteViewModel(noteUseCase)

        viewModel.notes.observeForever(observerNotes)
    }

    @Test
    fun `Test For Observe Changes With ViewModel`() = testDispatcher.runBlockingTest {
        val notes = viewModel.notes.getOrAwaitValue()
        notes shouldHaveSize TestUtils.notes.size
        verify { observerNotes.onChanged(TestUtils.notes) }
    }


    // We are not using an observer to this action, that's the reason why we just execute the delete action
    // and we verify if the usecase method is called
    @Test
    fun `Test For Delete Note With ViewModel`() = testDispatcher.runBlockingTest {
        viewModel.deleteNote(TestUtils.note)
        coVerify { noteUseCase.deleteNote(TestUtils.note) }
    }

}