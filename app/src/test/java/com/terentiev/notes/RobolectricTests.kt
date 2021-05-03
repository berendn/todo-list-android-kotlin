package com.terentiev.notes

import android.app.Application
import android.content.Intent
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.terentiev.notes.ui.CreateNoteActivity
import com.terentiev.notes.ui.NoteListActivity
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class RobolectricTests {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<NoteListActivity>()

    private val context = ApplicationProvider.getApplicationContext<Application>()

    @Test
    fun `clicking add note should start CreateNoteActivity`() {
        onView(withId(R.id.fab_new_todo)).perform(click())

        val expectedIntent = Intent(context, CreateNoteActivity::class.java)
        val actual: Intent = shadowOf(context).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }
}