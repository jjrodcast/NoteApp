package com.jjrodcast.data.base

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.jjrodcast.data.database.NoteDatabase
import com.jjrodcast.data.database.dao.NoteDao
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.util.concurrent.TimeUnit

abstract class DatabaseTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val countingTaskExecutorRule = CountingTaskExecutorRule()

    private lateinit var db: NoteDatabase
    protected lateinit var noteDao: NoteDao

    @Before
    @Throws(Exception::class)
    fun before() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()
        noteDao = db.noteDao()
    }

    @After
    @Throws(Exception::class)
    fun after() {
        db.close()
        drain()
    }

    fun drain() {
        countingTaskExecutorRule.drainTasks(5, TimeUnit.SECONDS)
    }
}