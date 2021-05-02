package com.terentiev.notes

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnitRunner
import com.terentiev.notes.data.NoteRecord
import com.terentiev.notes.utils.NoteRecordsFilter
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.beans.HasPropertyWithValue
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FilterTest {

    val testData = listOf(
        NoteRecord(id = null, title = "1", content = "tree"),
        NoteRecord(id = null, title = "2", content = "car"),
        NoteRecord(id = null, title = "3", content = "tower"),
        NoteRecord(id = null, title = "4", content = "street"),
        NoteRecord(id = null, title = "5", content = "donkey")
    )

    @Test
    fun emptyListIsEmpty() {
        assert(NoteRecordsFilter.filter(emptyList(), "").isEmpty())
    }

    @Test
    fun emptyFilterReturnsAllNotes() {
        val filteredList = NoteRecordsFilter.filter(testData, "")
        Assert.assertEquals(testData, filteredList)
    }

    @Test
    fun filterWorksOnTitle() {
        val filteredList = NoteRecordsFilter.filter(testData, "5")
        MatcherAssert.assertThat(
            filteredList,
            CoreMatchers.everyItem(
                HasPropertyWithValue.hasProperty(
                    "title",
                    CoreMatchers.containsString("5")
                )
            )
        )
    }

    @Test
    fun filterWorksOnContent() {
        val filteredList = NoteRecordsFilter.filter(testData, "t")
        MatcherAssert.assertThat(
            filteredList,
            CoreMatchers.everyItem(
                HasPropertyWithValue.hasProperty(
                    "content",
                    CoreMatchers.containsString("t")
                )
            )
        )
    }
}