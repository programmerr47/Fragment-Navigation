package com.github.programmerr47.fragmentnavigation

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.*

class NavigationDispatcher(
    private val activity: FragmentActivity,
    @IdRes private val containerId: Int
) : Navigation,
    BackListener {

    override fun changeBackStack(vararg fragmentCreators: () -> Fragment) {
        if (fragmentCreators.size == 1) {
            newRootFragment(fragmentCreators[0])
        } else if (fragmentCreators.size > 1) {
            activity.supportFragmentManager.run {
                clearBackStack()
                fragmentCreators
                    .map { it.invoke() }
                    .forEach { addFragment(it) }
                executePendingTransactions()
            }
        }
    }

    private fun newRootFragment(fragmentCreator: () -> Fragment) = newRootFragment(fragmentCreator())

    override fun newRootFragment(fragment: Fragment) {
        activity.supportFragmentManager.clearBackStack()
        changeFragment(fragment, backStack = false)
    }

    override fun changeFragment(fragment: Fragment, backStack: Boolean) = activity.run {
        supportFragmentManager.commitTransaction {
            if (backStack) placeToBackStack()
            replace(containerId, fragment)
        }
    }

    override fun back() = activity.run {
        onBackPressed()
    }

    override fun showDialog(dialog: DialogFragment, requestCode: Int) {
        dialog.setTargetFragment(null, requestCode)
        dialog.show(activity.supportFragmentManager, null)
    }

    override fun returnResult(args: Bundle) {
        back()
        (getCurrentFragment() as? FragmentResultListener)?.onFragmentResult(args)
    }

    override fun onBackPressed() = (getCurrentFragment() as? BackListener
        ?: EMPTY_BACK_LISTENER).onBackPressed()

    private fun getCurrentFragment() = activity.supportFragmentManager.findFragmentById(containerId)

    private fun FragmentManager.clearBackStack() {
        if (!isBackStackEmpty()) {
            val first = getBackStackEntryAt(0)
            popBackStackImmediate(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun FragmentManager.isBackStackEmpty() = backStackEntryCount == 0

    private fun FragmentTransaction.placeToBackStack() {
        addToBackStack(null)
        setTransition(FragmentTransaction.TRANSIT_NONE)
    }

    private fun FragmentManager.commitTransaction(block: FragmentTransaction.() -> Unit) {
        beginTransaction().apply { block() }.commit()
    }

    companion object {
        private val EMPTY_BACK_LISTENER = object : BackListener {
            override fun onBackPressed() = false
        }
    }
}