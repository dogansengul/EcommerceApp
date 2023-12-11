package com.example.ecommerceapp.presentation.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecoration(private val margin: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val layoutManager = parent.layoutManager as? GridLayoutManager

        if (layoutManager != null && layoutManager.orientation == RecyclerView.VERTICAL) {
            val spanCount = layoutManager.spanCount
            val column = position % spanCount

            if (column == 0) {
                outRect.right = margin / 2
                outRect.left = margin
            } else {
                outRect.left = margin / 2
                outRect.right = margin
            }

            outRect.top = margin
            outRect.bottom = margin / 2
        }
    }
}