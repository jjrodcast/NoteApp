package com.jjrodcast.data.daos

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.jjrodcast.data.base.DatabaseTest
import com.jjrodcast.data.utils.TestUtils
import com.jjrodcast.data.utils.ZERO
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@MediumTest
@RunWith(AndroidJUnit4::class)
class NoteDaoTest : DatabaseTest() {

    @Test
    fun testEmptyDatabase() = runBlocking {
        val elements = noteDao.getAllNotes().take(1).toList().flatten()
        elements shouldHaveSize ZERO.toInt()
        Unit
    }

    @Test
    fun testInsertNote() = runBlocking {
        val noteEntity = TestUtils.noteEntity
        noteDao.saveNote(noteEntity)
        val id = noteDao.getNoteById(noteEntity.id).take(1).toList().firstOrNull()?.id ?: ZERO
        id shouldNotBeEqualTo ZERO
        Unit
    }

    @Test
    fun testDeleteNote() = runBlocking {
        val noteEntity = TestUtils.noteEntity
        noteDao.delete(noteEntity)
        val id = noteDao.getNoteById(noteEntity.id).take(1).toList().firstOrNull()?.id ?: ZERO
        id shouldBeEqualTo ZERO
        Unit
    }

    @Test
    fun testUpdateNote() = runBlocking {
        val noteEntity = TestUtils.noteEntity
        val otherNoteEntity = noteEntity.copy(title = TestUtils.randomTitle)
        noteDao.saveNote(noteEntity)
        noteDao.updateNote(otherNoteEntity)

        val updatedTitle =
            noteDao.getNoteById(noteEntity.id).take(1).toList().firstOrNull()?.title
                ?: noteEntity.title

        updatedTitle shouldBeEqualTo otherNoteEntity.title
        Unit
    }

    @After
    override fun after() {
        super.after()
        countingTaskExecutorRule.drainTasks(200, TimeUnit.MILLISECONDS)
        countingTaskExecutorRule.isIdle shouldBeEqualTo true
    }
}