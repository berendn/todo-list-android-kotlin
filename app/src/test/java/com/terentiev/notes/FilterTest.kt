package com.terentiev.notes


import com.terentiev.notes.data.NoteRecord
import com.terentiev.notes.utils.NoteRecordsFilter
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.everyItem
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.beans.HasPropertyWithValue.hasProperty
import org.junit.Assert.assertEquals
import org.junit.Test

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
        assertEquals(testData, filteredList)
    }

    @Test
    fun filterWorksOnTitle() {
        val filteredList = NoteRecordsFilter.filter(testData, "5")
        assertThat(filteredList, everyItem(hasProperty("title", containsString("5"))))
    }

    @Test
    fun filterWorksOnContent() {
        val filteredList = NoteRecordsFilter.filter(testData, "t")
        assertThat(filteredList, everyItem(hasProperty("content", containsString("t"))))
    }
}
