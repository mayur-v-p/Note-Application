package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NoteUseCases
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(

    private val noteUseCases : NoteUseCases
) : ViewModel(){

    private val _state = mutableStateOf(NotesState())
    val state : State<NotesState> = _state

    private var recentlyDeletedNote : Note? = null
    private var getNotesJob : Job? = null

    init {

        getNote(NoteOrder.Date(OrderType.Descending))

    }

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order ->{

                if (state.value.noteOrder == event.noteOrder &&
                        state.value.noteOrder.orderType == event.noteOrder.orderType
                ){
                    return
                }
                getNote(event.noteOrder)

            }
            is NotesEvent.DeleteNote ->{
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }

            }
            is NotesEvent.RestoreNote ->{

                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }

            }
            is NotesEvent.ToggleOrderSelection ->{

                _state.value = _state.value.copy(
                    isOrderSelectionVisible = !state.value.isOrderSelectionVisible
                )

            }
        }
    }

    private fun getNote(noteOrder: NoteOrder){
        getNotesJob?.cancel()

        getNotesJob =  noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )

            }
            .launchIn(viewModelScope)
    }

}