package dev.kisurin.favoriteplaces.utils

import android.util.Log

const val TAG = "FavoritePlaces"

fun <T> T.log(message: String? = null) = also {
    val text = message?.let { "$it: " } ?: ""
    Log.d(TAG, "$text$this")
}

fun <T> T.log(action: (T) -> String?) = log(action(this))