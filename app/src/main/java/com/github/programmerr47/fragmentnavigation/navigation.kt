package com.github.programmerr47.fragmentnavigation

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment

interface NavigationProvider {
    fun provideNavigation(): Navigation
}

interface Navigation {
    fun addFragment(fragment: Fragment) = changeFragment(fragment, backStack = true)
    fun changeFragment(fragment: Fragment, backStack: Boolean)

    fun newRootFragment(fragment: Fragment)
    fun changeBackStack(vararg fragmentCreators: () -> Fragment)

    fun back()
    fun showDialog(dialog: DialogFragment, requestCode: Int)
    fun returnResult(args: Bundle)
}

interface FragmentResultListener {
    fun onFragmentResult(args: Bundle)
}

interface BackListener {
    fun onBackPressed(): Boolean
}