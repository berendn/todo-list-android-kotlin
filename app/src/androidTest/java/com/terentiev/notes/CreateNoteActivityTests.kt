package com.terentiev.notes

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.terentiev.notes.data.NoteDao
import com.terentiev.notes.data.NoteDatabase
import com.terentiev.notes.data.NoteRecord
import com.terentiev.notes.data.NoteRepository
import com.terentiev.notes.ui.CreateNoteActivity
import com.terentiev.notes.ui.NoteListActivity
import com.terentiev.notes.utils.Constants
import io.mockk.*
import kotlinx.android.synthetic.main.content_create_note.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class CreateNoteActivityTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(CreateNoteActivity::class.java)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun mockedDaoTest(){
        val noteId = 1L

        val note = NoteRecord(
            noteId,
            "Note title",
            "note content",
            0
        )

        val mockDao = mockk<NoteDao>(relaxed = true)
        every {
            mockDao.getActiveNotes()
        } returns MutableLiveData(listOf(note))

        val repo = spyk(NoteRepository(mockDao))

        verify {
            mockDao.getActiveNotes()
        }

    }

}