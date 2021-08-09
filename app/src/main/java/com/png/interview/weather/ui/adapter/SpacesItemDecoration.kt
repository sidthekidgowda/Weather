package com.png.interview.weather.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class SpacesItemDecoration(val space: Int = DEFAULT_SPACE) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = space
        outRect.bottom = space
    }

    companion object {
        const val DEFAULT_SPACE = 16
    }
}