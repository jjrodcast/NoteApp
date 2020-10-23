package com.jjrodcast.note.features.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jjrodcast.domain.entities.Note
import com.jjrodcast.note.R
import com.jjrodcast.note.databinding.FragmentCreateNoteBinding
import com.jjrodcast.note.features.create.viewmodel.CreateUpdateNoteViewModel
import com.jjrodcast.note.features.create.viewmodel.CreateUpdateState
import com.jjrodcast.note.utils.createRandomColor
import com.jjrodcast.note.utils.isNew
import com.jjrodcast.note.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CreateNoteFragment : Fragment() {

    private val args by navArgs<CreateNoteFragmentArgs>()
    private val noteViewModel by viewModels<CreateUpdateNoteViewModel>()

    private var note = Note()

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureBackNavigation()
        observeChanges()
        configureSaveEvent()
        configureSearchNote()
    }

    private fun configureBackNavigation() = with(binding) {
        backIcon.setOnClickListener { navigateToHome() }
    }

    private fun configureSaveEvent() = with(binding) {
        btnSaveNote.setOnClickListener {
            note = note.copy(
                title = titleNote.text.toString(),
                description = descriptionNote.text.toString(),
                color = if (note.id.isNew()) requireContext().createRandomColor() else note.color,
                date = if (note.id.isNew()) Date().time else note.date
            )
            noteViewModel.insertOrUpdateNote(note)
        }
    }

    private fun configureSearchNote() = noteViewModel.getNote(args.noteId)

    private fun observeChanges() = with(binding) {
        noteViewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is CreateUpdateState.SearchResponse -> {
                    note = it.note
                    titleNote.setText(note.title)
                    descriptionNote.setText(note.description)
                }
                is CreateUpdateState.ModelNotValid -> toast(getString(R.string.model_not_valid))
                is CreateUpdateState.Done -> {
                    navigateToHome()
                    toast(getString(R.string.model_saved))
                }
                else -> Unit
            }
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(CreateNoteFragmentDirections.actionToListNotes())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}