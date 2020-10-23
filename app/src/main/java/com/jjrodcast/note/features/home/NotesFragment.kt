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
import com.jjrodcast.note.features.home.viewmodel.ListNoteViewModel
import com.jjrodcast.note.utils.GridInsideItemDecoration
import com.jjrodcast.note.utils.SPAN_COUNT
import com.jjrodcast.note.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

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
            R.id.delete -> note?.let { noteViewModel.deleteNote(it) }
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
        noteViewModel.notes.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}