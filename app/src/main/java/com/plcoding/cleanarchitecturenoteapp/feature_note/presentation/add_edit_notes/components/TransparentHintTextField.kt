package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_notes.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import org.w3c.dom.Text

@Composable
fun TransparentHintTextFiend(
    text : String,
    hint : String,
    modifier: Modifier = Modifier,
    isHintVisible : Boolean = true,
    onValueChange : (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine : Boolean = false,
    onFocusChange : (FocusState) -> Unit

){
    Box (
        modifier = Modifier
    ){
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                }
        )
        if (isHintVisible) {
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }
    }

    

}