package com.absolute.cinema.ui.utils

import android.content.Context
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.view.setMargins
import com.absolute.cinema.R

object UiUtils {

    fun initGridLayout(gridLayout: GridLayout, context: Context) {
        gridLayout.apply {
            columnCount = 14
            removeAllViews()
        }

        for (i in 0 until 14) {
            val circleView = ImageView(context).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 48
                    height = 48
                    setMargins(8)
                }
                setImageResource(R.drawable.circle)
                scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            gridLayout.addView(circleView)
        }
    }

}