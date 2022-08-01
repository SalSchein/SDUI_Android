package com.example.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ScreenDto (
    val children: List<ElementDto>? = null
) 

@JsonClass(generateAdapter = true)
data class ElementDto (
    val children: List<ElementDto>? = null,
    val label: String? = null,
    val viewtype: ViewType? = null,
    val default: String? = null,
    val data: String? = null,
    val height: Int? = 20,
    val width: Int? = 20
)

enum class ViewType {
    BUTTON,
    CHECKBOX,
    FORM,
    TEXT,
    TEXTFIELD,
    SPACER
}

