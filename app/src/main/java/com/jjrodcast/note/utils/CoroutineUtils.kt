package com.jjrodcast.note.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun ui(block: suspend () -> Unit) {
    withContext(Dispatchers.Main) { block.invoke() }
}

suspend fun io(block: suspend () -> Unit) {
    withContext(Dispatchers.IO) { block.invoke() }
}

fun Any.ui(block: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.Main) { block.invoke() }
}

fun Any.io(block: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.IO) { block.invoke() }
}