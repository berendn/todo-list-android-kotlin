package com.terentiev.notes.utils

import android.widget.Filter
import com.terentiev.notes.data.NoteRecord

object NoteRecordsFilter {

    fun filter(input: List<NoteRecord>, filter:String):List<NoteRecord>{
        return if (filter.isEmpty()) {
            input
        } else {
            val filteredList = arrayListOf<NoteRecord>()
            for (row in input) {
                if (row.title.orEmpty().toLowerCase().contains(filter.toLowerCase())
                    || row.content.orEmpty().toLowerCase().contains(filter.toLowerCase())
                ) {
                    filteredList.add(row)
                }
            }
            filteredList
        }
    }
}