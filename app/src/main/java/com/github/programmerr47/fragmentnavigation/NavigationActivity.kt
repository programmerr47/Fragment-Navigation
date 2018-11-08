package com.github.programmerr47.fragmentnavigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gihub.programmerr47.fragmentnavigation.R

abstract class NavigationActivity : AppCompatActivity(),
    NavigationProvider, StatusBarProvider {
    protected open val containerId = R.id.fragmentContainer

    protected val dispatcher: NavigationDispatcher by lazy {
        NavigationDispatcher(
            this,
            containerId
        )
    }

    protected val statusBarManager: StatusBarManager by lazy {
        StatusBarManager(this, true)
    }

    override fun provideNavigation() = HideKeyboardNavigation(dispatcher)

    override fun provideStatusBarOwner() = statusBarManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(statusBarManager)
    }

    override fun onBackPressed() {
        if (!dispatcher.onBackPressed()) {
            super.onBackPressed()
        }
    }
}