package com.github.programmerr47.fragmentnavigation

interface StatusBarProvider {
    fun provideStatusBarOwner(): StatusBarOwner
}

interface StatusBarOwner {
    val statusBarHeight: Int
    val isStatusBarTranslucent: Boolean
    var isStatusBarLight: Boolean
}