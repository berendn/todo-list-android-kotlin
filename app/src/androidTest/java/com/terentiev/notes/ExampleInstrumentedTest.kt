package com.terentiev.notes

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.terentiev.notes.data.NoteDao
import com.terentiev.notes.data.NoteDatabase
import com.terentiev.notes.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

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
            }
        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.terentiev.notes", appContext.packageName)
    }
}
