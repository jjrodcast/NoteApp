package com.jjrodcast.note.features.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jjrodcast.domain.entities.Note
import com.jjrodcast.note.R
import com.jjrodcast.note.databinding.FragmentNotesBinding
import com.jjrodcast.note.features.home.adapter.NoteAdapter
import com.jjrodcast.note.features.home.states.NoteListIntent
import com.jjrodcast.note.features.home.states.NoteListState
import com.jjrodcast.note.mvi.View as MviView
import com.jjrodcast.note.features.home.viewmodel.ListNoteViewModel
import com.jjrodcast.note.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment(), MviView<NoteListState> {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    // Note to be deleted
    private var note: Note? = null

    private val noteViewModel by viewModels<ListNoteViewModel>()
    private val noteAdapter by lazy { NoteAdapter(::onClickNote, ::onLongClickNote) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        configureCreateNote()
        configureRecycler()
        observeNoteChanges()
        fetchNotes()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        activity?.menuInflater?.inflate(R.menu.menu_context_item, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> deleteNote()
            else -> Unit
        }
        return true
    }

    private fun configureRecycler() = with(binding) {
        rvNotes.apply {
            setHasFixedSize(true)
            addItemDecoration(GridInsideItemDecoration(context))
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            isNestedScrollingEnabled = false
            adapter = noteAdapter
        }
        registerForContextMenu(rvNotes)
    }

    private fun observeNoteChanges() {
        observeState(noteViewModel.state) { render(it) }
    }

    private fun fetchNotes() {
        onLifeCycleLaunch {
            noteViewModel.intent.send(NoteListIntent.FetchNotes)
        }
    }

    private fun deleteNote() {
        onLifeCycleLaunch {
            note?.let { noteViewModel.intent.send(NoteListIntent.DeleteNote(it)) }
        }
    }

    private fun configureCreateNote() = with(binding) {
        fabCreateNote.setOnClickListener {
            it.findNavController().navigate(NotesFragmentDirections.actionToCreateUpdateNote())
        }
    }

    private fun onClickNote(note: Note) {
        findNavController().navigate(NotesFragmentDirections.actionToCreateUpdateNote(note.id))
    }

    private fun onLongClickNote(view: View, note: Note) {
        this.note = note
        view.showContextMenu()
    }

    override fun render(state: NoteListState) = when (state) {
        is NoteListState.Loading -> toast(getString(R.string.loading))
        is NoteListState.Success -> noteAdapter.submitList(state.notes)
        is NoteListState.Failure -> toast(getString(R.string.error_message))
        is NoteListState.Done -> toast(getString(R.string.done))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}