package com.terentiev.notes

import android.content.Context
import com.terentiev.notes.data.NoteDatabase
import com.terentiev.notes.data.NoteRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TestUtils {

    companion object {
        fun clearDb(context: Context) {
            val db = NoteDatabase.getInstance(context)
            val noteDao = db!!.todoDao()
            runBlocking {
                this.launch(Dispatchers.IO) {
                    noteDao.deleteAllNotes()
                }
            }
        }
    }

}