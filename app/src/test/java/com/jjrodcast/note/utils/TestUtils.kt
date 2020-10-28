package com.jjrodcast.note.utils

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.jjrodcast.domain.entities.Note
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

const val ZERO = 0L

object TestUtils {
    val note = Note(
        id = 1,
        title = "title",
        description = "description",
        color = 101010,
        date = Date().time
    )

    val notes = listOf(note, note.copy(id = 2))
}


@Suppress("UNCHECKED_CAST")
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2L,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    observeForever(observer)

    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData never change")
    }

    return data as T
}
