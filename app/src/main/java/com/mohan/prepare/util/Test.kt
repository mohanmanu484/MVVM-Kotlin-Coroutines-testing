package com.mohan.prepare.util

import com.mohan.prepare.adapter.ListAdapter
import com.mohan.prepare.model.News

class Test {

    internal fun t() {
        object : ListAdapter<News>(null!!) {
            override fun getViewType(position: Int): Int {
                return 0
            }
        }
    }


}
