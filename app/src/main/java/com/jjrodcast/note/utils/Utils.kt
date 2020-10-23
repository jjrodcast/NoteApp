package com.jjrodcast.note.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.jjrodcast.note.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

const val defaultFormat = "MMM dd, yyyy"

fun <E> ArrayList<E>.update(elements: Collection<E>) {
    this.clear()
    this.addAll(elements)
}

fun Long.isNew() = this == 0L

fun Long.toDateString(format: String = defaultFormat): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale("es", "PE"))
    return simpleDateFormat.format(Date(this)).capitalize()
}

fun Context.getCompatColor(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun Context.createRandomColor(): Int {
    val color = when (Random.nextInt(from = 1, until = 8)) {
        1 -> R.color.random_1
        2 -> R.color.random_2
        3 -> R.color.random_3
        4 -> R.color.random_4
        5 -> R.color.random_5
        6 -> R.color.random_6
        else -> R.color.random_7
    }
    return getCompatColor(color)
}