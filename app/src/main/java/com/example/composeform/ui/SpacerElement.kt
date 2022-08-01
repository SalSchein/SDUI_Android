package com.example.composeform.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.ElementDto
//import java.lang.reflect.Modifier

class SpacerElement(val elementDto: ElementDto) : ComposableElement {
    @Composable
    override fun compose(hoist: Map<String, MutableState<String>>) {
 //       (elementDto.label?:"")
        val spacerHeight = elementDto.height?:0
        val spacerWidth = elementDto.width?:0
        Spacer(modifier = Modifier
            .height(spacerHeight.dp)
            .width(spacerWidth.dp))
    }

    override fun getHoist(): Map<String, MutableState<String>> {
        return mapOf()
    }
}
