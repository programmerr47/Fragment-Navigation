package com.github.programmerr47.fragmentnavigation

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * A FrameLayout which memorizes the window insets and propagates them to the child views as soon as
 * they are added. Use this layout as a fragment container in place of a standard FrameLayout to
 * propagate window insets to attached fragments.
 *
 * @author Christophe Beyls
 */
class FitsSystemWindowsFrameLayout : FrameLayout {

    private val windowInsets = Rect()
    private val tempInsets = Rect()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun fitSystemWindows(insets: Rect): Boolean {
        windowInsets.set(insets)
        super.fitSystemWindows(insets)
        return false
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        super.addView(child, index, params)
        tempInsets.set(windowInsets)
        super.fitSystemWindows(tempInsets)
    }
}