package com.jjrodcast.note.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun ui(block: suspend () -> Unit) {
    withContext(Dispatchers.Main) { block.invoke() }
}

suspend fun io(block: suspend () -> Unit) {
    withContext(Dispatchers.IO) { block.invoke() }
}
