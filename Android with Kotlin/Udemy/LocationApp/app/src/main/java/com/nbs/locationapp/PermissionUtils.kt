package com.nbs.locationapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionUtils {
    fun hasPermission(context: Context, permission: String): Boolean {
        return (ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED)

    }

    fun hasLocationPermission(context: Context): Boolean {
        val hasLocationPermission =
            (hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    && hasPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION))

        return hasLocationPermission
    }

    fun requestPermission(context: Context, permission: String) {

    }
}