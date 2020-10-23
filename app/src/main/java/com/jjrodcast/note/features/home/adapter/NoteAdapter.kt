package com.jjrodcast.note.features.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jjrodcast.domain.entities.Note
import com.jjrodcast.note.R
import com.jjrodcast.note.databinding.ItemNoteBinding
import com.jjrodcast.note.utils.inflate
import com.jjrodcast.note.utils.toDateString
import com.jjrodcast.note.utils.update

class NoteAdapter(
    private val clickEvent: (note: Note) -> Unit = { },
    private val onLongClickEvent: (v: View, note: Note) -> Unit = { _, _ -> }
) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val notes = arrayListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteBinding.bind(parent.inflate(R.layout.item_note)))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(notes[position])

    override fun getItemCount() = notes.size

    fun submitList(notes: List<Note>) {
        this.notes.update(notes)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val itemBinding: ItemNoteBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(note: Note) = with(itemBinding) {
            itemBinding.root.setCardBackgroundColor(note.color)
            title.text = note.title
            date.text = note.date.toDateString()
            root.setOnClickListener { clickEvent.invoke(note) }
            root.setOnLongClickListener {
                onLongClickEvent.invoke(it, note)
                true
            }
        }
    }
}