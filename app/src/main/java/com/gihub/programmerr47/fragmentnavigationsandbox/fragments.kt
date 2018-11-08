package com.gihub.programmerr47.fragmentnavigationsandbox

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.github.programmerr47.fragmentnavigation.NavigationFragment

class SampleFragment : NavigationFragment() {
    var fragmentId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_default, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.bind<TextView>(R.id.tv_status) { text = getString(R.string.fragment_status_title, fragmentId.toString()) }
        view.bind<Button>(R.id.btn_new_fragment) { setOnClickListener { addFragment(SampleFragment().also {
            it.fragmentId = fragmentId + 1
        }) } }
        view.bind<Button>(R.id.btn_new_root_fragment) { setOnClickListener { newRootFragment(SampleFragment()) } }
    }
}

private fun <T : View> View.bind(@IdRes id: Int, init: T.() -> Unit) = findViewById<T>(id).apply(init)