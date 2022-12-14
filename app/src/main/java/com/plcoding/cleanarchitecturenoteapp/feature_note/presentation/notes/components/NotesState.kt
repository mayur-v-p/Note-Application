package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.components

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType

data class NotesState(
    val notes : List<Note> = emptyList(),
    val noteOrder : NoteOrder = NoteOrder.Date(orderType = OrderType.Descending),
    val isOrderSelectionVisible : Boolean = false
)
