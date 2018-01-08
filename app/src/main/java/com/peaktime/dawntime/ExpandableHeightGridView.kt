package com.peaktime.dawntime

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.View.MeasureSpec
import android.widget.GridView


/**
 * Created by minhyoung on 2018. 1. 8..
 */
class ExpandableHeightGridView : GridView{
    private var expanded = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context,attrs)

    constructor(context: Context, attrs: AttributeSet,defStyle: Int) : super(context,attrs,defStyle)

    fun isExpanded(): Boolean {
        return expanded
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // HACK! TAKE THAT ANDROID!
        if (isExpanded()) {
            // Calculate entire height by providing a very large height hint.
            // View.MEASURED_SIZE_MASK represents the largest height possible.
            val expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK,
                    MeasureSpec.AT_MOST)
            super.onMeasure(widthMeasureSpec, expandSpec)

            val params = getLayoutParams()
            params.height = getMeasuredHeight()
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    fun setExpanded(expanded: Boolean) {
        this.expanded = expanded
    }
}