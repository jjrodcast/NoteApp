package com.jjrodcast.domain.entities

import java.io.Serializable


data class Note(
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    val color: Int = 0,
    val date: Long = 0
) : Serializable {

    fun isValid() = title.isNotEmpty() && description.isNotEmpty()
}