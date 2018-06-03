package com.mohan.prepare.adapter

import android.app.Activity

class Test :Activity{

    private lateinit var s: String


    constructor(s: String) {
        this.s = s
    }

    constructor() {}
}
