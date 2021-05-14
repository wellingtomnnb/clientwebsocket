package com.example.testewebsocket.Util

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class NoScrollLayoutManager (context: Context): LinearLayoutManager(context) {
    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun getOrientation(): Int {
        return super.getOrientation()

    }
}