package com.jjrodcast.note.viewmodels

import androidx.lifecycle.Observer
import com.jjrodcast.domain.usecases.NoteUseCase
import com.jjrodcast.note.base.BaseTest
import com.jjrodcast.note.features.create.viewmodel.CreateUpdateNoteViewModel
import com.jjrodcast.note.features.create.viewmodel.CreateUpdateState
import com.jjrodcast.note.utils.TestUtils
import com.jjrodcast.note.utils.getOrAwaitValue
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CreateUpdateNoteViewModelTest : BaseTest() {

    private val noteUseCase = mockk<NoteUseCase>(relaxed = true)
    private val observer = mockk<Observer<CreateUpdateState>>(relaxed = true)
    private lateinit var viewModel: CreateUpdateNoteViewModel

    @Before
    override fun setup() {
        super.setup()
        every { noteUseCase.getNoteById(any()) } returns flowOf(TestUtils.note)
        coEvery { noteUseCase.insertNote(any()) } just Runs
        coEvery { noteUseCase.updateNote(any()) } just Runs

        viewModel = CreateUpdateNoteViewModel(noteUseCase)

        viewModel.state.observeForever(observer)
    }

    @Test
    fun `Test For State Done On Insert Note With ViewModel`() = testDispatcher.runBlockingTest {
        viewModel.insertOrUpdateNote(TestUtils.noteNew)
        verify { observer.onChanged(CreateUpdateState.Done) }
    }

    @Test
    fun `Test For State ModelNotValid On Insert Note With ViewModel`() =
        testDispatcher.runBlockingTest {
            viewModel.insertOrUpdateNote(TestUtils.noteInvalid)
            viewModel.state.getOrAwaitValue()
            verify { observer.onChanged(CreateUpdateState.ModelNotValid) }
        }

    @Test
    fun `Test For State NotFound On Get Note With ViewModel`() = testDispatcher.runBlockingTest {
        //We get the new note id, because we are creating a new note
        viewModel.getNote(TestUtils.noteNew.id)
        viewModel.state.getOrAwaitValue()
        verify { observer.onChanged(CreateUpdateState.NotFound) }
    }

    @Test
    fun `Test For State SearchResponse On Get Note With ViewModel`() =
        testDispatcher.runBlockingTest {
            viewModel.getNote(TestUtils.note.id)
            val state = viewModel.state.getOrAwaitValue()
            with(state as CreateUpdateState.SearchResponse) {
                // We check the values of the model
                note.id shouldBeEqualTo TestUtils.note.id
                note.title shouldBeEqualTo TestUtils.note.title
                note.description shouldBeEqualTo TestUtils.note.description
                note.color shouldBeEqualTo TestUtils.note.color
                note.date shouldBeEqualTo TestUtils.note.date
            }
        }
}