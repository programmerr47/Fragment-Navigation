package com.gihub.programmerr47.fragmentnavigationsandbox

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.widget.SwitchCompat
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import com.github.programmerr47.fragmentnavigation.NavigationFragment

open class BaseSampleFragment : NavigationFragment() {
    var fragmentId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_default_fullscreen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.bind<TextView>(R.id.tv_status) { text = getString(R.string.fragment_status_title, fragmentId.toString()) }
        view.bind<Button>(R.id.btn_new_fragment) { setOnClickListener { addFragment(createNewFragment(view).also {
            it.fragmentId = fragmentId + 1
        }) } }
        view.bind<Button>(R.id.btn_new_root_fragment) { setOnClickListener { newRootFragment(createNewFragment(view)) } }
    }

    private fun createNewFragment(view: View): BaseSampleFragment {
        return if (view.bind<SwitchCompat>(R.id.sc_full_screen_fragment).isChecked) {
            BaseSampleFragment()
        } else {
            ToolbarFragment()
        }
    }
}

class ToolbarFragment : BaseSampleFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_default, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.bind<Toolbar>(R.id.toolbar) { title = "Test" }
    }
}

private fun <T : View> View.bind(@IdRes id: Int, init: T.() -> Unit = {}) = findViewById<T>(id).apply(init)