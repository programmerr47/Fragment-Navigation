package com.github.programmerr47.fragmentnavigation

import android.app.Activity
import android.arch.lifecycle.Lifecycle.Event.ON_CREATE
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

class StatusBarManager(
    private val activity: Activity,
    private val isThemeTranslucent: Boolean = false
) : StatusBarOwner, LifecycleObserver {

    override val statusBarHeight: Int by lazy { computeStatusBarHeight() }

    override val isStatusBarTranslucent: Boolean by lazy { isThemeTranslucent && statusBarHeight > 0 }

    override var isStatusBarLight: Boolean
        get() = if (isMarshmallow()) {
            with(activity.window.decorView) {
                systemUiVisibility and SYSTEM_UI_FLAG_LIGHT_STATUS_BAR == systemUiVisibility
            }
        } else {
            false
        }
        set(value) {
            if (isMarshmallow()) {
                with(activity.window.decorView) {
                    systemUiVisibility = if (value) {
                        systemUiVisibility or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    } else {
                        systemUiVisibility and SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                    }
                }
            }
        }

    @OnLifecycleEvent(ON_CREATE)
    fun prepare() {
        if (isLollipop() && isStatusBarTranslucent) {
            activity.window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private fun computeStatusBarHeight(): Int {
        return if (isLollipop()) {
            var result = 0
            val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = activity.resources.getDimensionPixelSize(resourceId)
            }
            result
        } else {
            0
        }
    }
}