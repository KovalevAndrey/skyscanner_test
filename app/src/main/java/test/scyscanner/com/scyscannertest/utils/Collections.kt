package test.scyscanner.com.scyscannertest.utils

import java.util.*

fun <T> Collection<T>?.asArrayList() =
        when {
            this == null -> null
            this is ArrayList -> this
            else -> ArrayList(this)
        }