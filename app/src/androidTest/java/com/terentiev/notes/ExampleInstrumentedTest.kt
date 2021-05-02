package com.terentiev.notes

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
import com.terentiev.notes.ui.NoteListActivity
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
class ExampleInstrumentedTest {

    @Before
    fun clear() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val db = NoteDatabase.getInstance(context)
        val noteDao = db!!.todoDao()
        runBlocking {
            this.launch(Dispatchers.IO) {
                noteDao.deleteAllNotes()

                noteDao.saveNote(
                    NoteRecord(
                        id = null,
                        title = "Test title",
                        content = "Test content"
                    )
                )
            }
        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.terentiev.notes", appContext.packageName)
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(NoteListActivity::class.java)

    @Test
    fun hasTestNote() {
        onView(withText("Test title")).check(matches(isDisplayed()))
    }

    @Test
    fun opensTestNote() {
        onView(withText("Test title")).perform(click())
        onView(withId(R.id.et_todo_title)).check(matches(withText("Test title")))
    }

}
