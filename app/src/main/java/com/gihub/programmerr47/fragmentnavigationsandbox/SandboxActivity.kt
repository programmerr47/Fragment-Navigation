package com.gihub.programmerr47.fragmentnavigationsandbox

import android.os.Bundle
import com.github.programmerr47.fragmentnavigation.NavigationActivity

class SandboxActivity : NavigationActivity() {
    override val containerId: Int = R.id.fl_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sandbox)

        dispatcher.changeFragment(SampleFragment(), false)
    }
}
