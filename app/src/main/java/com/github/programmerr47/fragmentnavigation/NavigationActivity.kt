package com.github.programmerr47.fragmentnavigation

import android.support.v7.app.AppCompatActivity
import com.gihub.programmerr47.fragmentnavigation.R

abstract class NavigationActivity : AppCompatActivity(),
    NavigationProvider {
    protected open val containerId = R.id.fragmentContainer
    protected val dispatcher: NavigationDispatcher by lazy {
        NavigationDispatcher(
            this,
            containerId
        )
    }

    override fun provideNavigation() = dispatcher

    override fun onBackPressed() {
        if (!dispatcher.onBackPressed()) {
            super.onBackPressed()
        }
    }
}