package com.github.programmerr47.fragmentnavigation

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment

abstract class NavigationFragment(
    private val navigation: NavigationHolder = NavigationHolder(),
    private val statusBarOwner: StatusBarHolder = StatusBarHolder()
) : Fragment(), Navigation by navigation, StatusBarOwner by statusBarOwner {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation.actual =
                (context as? NavigationProvider)?.provideNavigation() ?:
                context as? Navigation

        statusBarOwner.actual =
                (context as? StatusBarProvider)?.provideStatusBarOwner() ?:
                context as? StatusBarOwner
    }
}

class NavigationHolder : Navigation {
    internal var actual: Navigation? = null

    override fun changeFragment(fragment: Fragment, backStack: Boolean) {
        actual?.changeFragment(fragment, backStack)
    }

    override fun newRootFragment(fragment: Fragment) {
        actual?.newRootFragment(fragment)
    }

    override fun changeBackStack(vararg fragmentCreators: () -> Fragment) {
        actual?.changeBackStack(*fragmentCreators)
    }

    override fun back() {
        actual?.back()
    }

    override fun showDialog(dialog: DialogFragment, requestCode: Int) {
        actual?.showDialog(dialog, requestCode)
    }

    override fun returnResult(args: Bundle) {
        actual?.returnResult(args)
    }
}

class StatusBarHolder : StatusBarOwner {
    internal var actual: StatusBarOwner? = null

    override val statusBarHeight get() = actual?.statusBarHeight ?: 0
    override val isStatusBarTranslucent get() = actual?.isStatusBarTranslucent ?: false
    override var isStatusBarLight
        get() = actual?.isStatusBarLight ?: false
        set(value) {
            actual?.isStatusBarLight = value
        }
}