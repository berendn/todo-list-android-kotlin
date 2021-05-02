package com.terentiev.notes

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.terentiev.notes.data.NoteDao
import com.terentiev.notes.data.NoteDatabase
import com.terentiev.notes.data.NoteRecord
import com.terentiev.notes.data.NoteRepository
import com.terentiev.notes.ui.NoteListActivity
import com.terentiev.notes.ui.NoteListAdapter
import kotlinx.android.synthetic.main.content_create_note.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4ClassRunner::class)
class EditNoteTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext


    @get:Rule
    val activityRule = ActivityScenarioRule(NoteListActivity::class.java)

    @Before
    fun setup() {
        TestUtils.clearDb(context)
        addTestContent()
    }

    private fun addTestContent() {
        val db = NoteDatabase.getInstance(context)
        val noteDao = db!!.todoDao()
        runBlocking {
            this.launch(Dispatchers.IO) {
                (1 until 20).forEach {
                    noteDao.saveNote(
                        NoteRecord(
                            id = null,
                            title = "Test title $it",
                            content = "Test content $it"
                        )
                    )
                }
            }
        }
    }

    @Test
    fun listIsVisible() {
        onView(withId(R.id.rv_todo_list)).check(matches(isDisplayed()))
    }

    @Test
    fun opensTestNote() {
        onView(withId(R.id.rv_todo_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<NoteListAdapter.ViewHolder>(
                18,
                click()
            )
        )
        onView(withId(R.id.et_todo_title)).check(matches(isDisplayed()))
    }

    @Test
    fun opensTestNote2() {
        onView(withText("Test title 1")).perform(click())
        onView(withId(R.id.et_todo_title)).check(matches(withText("Test title 1")))
    }

}