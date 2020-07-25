package com.example.voiceeffects.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.checkPermissions(permissions: Array<String>) =
    permissions.takeIf { it.isNotEmpty() }
        ?.map { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_DENIED }
        ?.firstOrNull { it.not() }
        ?.let { true }
        ?: false

fun Context.checkSelfPermissions(permissions: Array<String>): Boolean {
    for (item in permissions) {
        if (ContextCompat.checkSelfPermission(
                this,
                item
            ) != PackageManager.PERMISSION_GRANTED
        )
            return false
    }
    return true
}
