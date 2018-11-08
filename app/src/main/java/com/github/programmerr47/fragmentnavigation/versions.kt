package com.github.programmerr47.fragmentnavigation

import android.os.Build.VERSION_CODES
import android.os.Build.VERSION
import android.support.annotation.RestrictTo
import android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP

@RestrictTo(LIBRARY_GROUP)
fun isLollipop() = VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP

@RestrictTo(LIBRARY_GROUP)
fun isMarshmallow() = VERSION.SDK_INT >= VERSION_CODES.M