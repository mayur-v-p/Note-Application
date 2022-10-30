package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_notes.components

data class NoteTextFieldState(
    val text : String = "",
    val hint : String = "",
    val isHintVisible : Boolean = true
)
