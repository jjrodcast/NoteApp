package com.jjrodcast.note.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jjrodcast.note.R

class GridInsideItemDecoration(
    context: Context,
    @DimenRes verticalDimension: Int = R.dimen.ds_inset_medium,
    @DimenRes horizontalDimension: Int = R.dimen.ds_inset_medium
) : RecyclerView.ItemDecoration() {

    private val vDimen = context.resources.getDimensionPixelSize(verticalDimension) / 2
    private val hDimen = context.resources.getDimensionPixelSize(horizontalDimension) / 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val children = parent.adapter?.itemCount ?: 1
        val columns = parent.getItemCount()
        // We add 1 to be on range [1 - itemCount]
        val position = parent.getChildAdapterPosition(view) + 1
        val rows = (children / columns) + children % columns

        outRect.set(applyOffset(position, rows, columns))

    }

    private fun applyOffset(position: Int, totalRows: Int, columns: Int) = when {
        isFirstRow(position, columns) -> resolverOffsetFirstRow(position, columns, totalRows == 1)
        isLastRow(position, totalRows, columns) -> resolverOffsetLastRow(position, columns)
        else -> resolverOffsetCenterRow(position, columns)
    }

    private fun RecyclerView.getItemCount() = when (layoutManager) {
        is GridLayoutManager -> (layoutManager as GridLayoutManager).spanCount
        is StaggeredGridLayoutManager -> (layoutManager as StaggeredGridLayoutManager).spanCount
        else -> 1
    }

    private fun isFirstRow(position: Int, columns: Int) = position <= columns

    private fun isLastRow(position: Int, totalRows: Int, columns: Int) =
        ((position / columns) + position % columns) == totalRows

    private fun isFirstColumn(position: Int, columns: Int) = (position % columns) == 1

    private fun isLastColumn(position: Int, columns: Int) = (position % columns) == 0

    private fun isCenter(position: Int, columns: Int) = (position % columns) !in 0..1

    private fun resolverOffsetFirstRow(position: Int, columns: Int, unique: Boolean): Rect {
        val offset = Rect()

        when {
            isFirstColumn(position, columns) -> offset.set(0, 0, hDimen, if (unique) 0 else vDimen)
            isCenter(position, columns) -> offset.set(hDimen, 0, hDimen, if (unique) 0 else vDimen)
            isLastColumn(position, columns) -> offset.set(hDimen, 0, 0, if (unique) 0 else vDimen)
            else -> offset.set(0, 0, 0, 0)
        }
        return offset
    }

    private fun resolverOffsetLastRow(position: Int, columns: Int): Rect {
        val offset = Rect()
        when {
            isFirstColumn(position, columns) -> offset.set(0, vDimen, hDimen, 0)
            isCenter(position, columns) -> offset.set(hDimen, vDimen, hDimen, 0)
            isLastColumn(position, columns) -> offset.set(hDimen, vDimen, 0, 0)
            else -> offset.set(0, 0, 0, 0)
        }
        return offset
    }

    private fun resolverOffsetCenterRow(position: Int, columns: Int): Rect {
        val offset = Rect()
        when {
            isFirstColumn(position, columns) -> offset.set(0, vDimen, hDimen, vDimen)
            isCenter(position, columns) -> offset.set(hDimen, vDimen, hDimen, vDimen)
            isLastColumn(position, columns) -> offset.set(hDimen, vDimen, 0, vDimen)
            else -> offset.set(0, 0, 0, 0)
        }
        return offset
    }
}
