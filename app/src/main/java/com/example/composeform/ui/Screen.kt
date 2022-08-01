package com.example.composeform.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.data.ElementDto
import com.example.data.ScreenDto
import com.example.data.ViewType


class Screen ( screenDto: ScreenDto ) {
    var elements = screenDto.children?.map {
        it.getComposableElement()
    } ?: listOf()

    @Composable
    fun compose() {
        // TRL 5/9/22: Outside Column removed, not sure why it was needed
//        Column {
            val fields = elements.map { it.getHoist() }
            Column {
                elements.zip(fields).map {
                    it.first.compose(it.second)
                }
            }
//        }
    }
}

interface ComposableElement {
    @Composable
    fun compose(hoist: Map<String, MutableState<String>>)

    fun getHoist(): Map<String, MutableState<String>>
}

class EmptyElement : ComposableElement {
    @Composable
    override fun compose(hoist: Map<String, MutableState<String>>) {
    }

    override fun getHoist(): Map<String, MutableState<String>> {
        return mapOf()
    }
}

fun ElementDto.getComposableElement(): ComposableElement {
    return when(this.viewtype) {
        ViewType.BUTTON -> ButtonElement(this)
        ViewType.CHECKBOX -> CheckBoxElement(this)
        ViewType.FORM -> FormElement(this)
        ViewType.TEXT -> TextElement(this)
        ViewType.TEXTFIELD -> TextFieldElement(this)
        ViewType.SPACER -> SpacerElement(this)
        else -> EmptyElement()
    }
}
