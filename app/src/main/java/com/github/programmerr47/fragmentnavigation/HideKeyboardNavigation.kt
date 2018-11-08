package com.github.programmerr47.fragmentnavigation

import android.support.v4.app.Fragment

class HideKeyboardNavigation(
    private val origin: Navigation
) : Navigation by origin {
    override fun changeFragment(fragment: Fragment, backStack: Boolean) {
        hideKeyboard()
        origin.changeFragment(fragment, backStack)
    }

    override fun back() {
        hideKeyboard()
        origin.back()
    }

    private fun hideKeyboard() {
        //logic of hiding keyboard
    }
}