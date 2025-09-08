package com.nbs.shoppinglist.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nbs.shoppinglist.MainActivity

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

    fun showRationale(activity: MainActivity, permission: String, rationaleMsg: String) {
        val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
            activity, permission
        )

        if (rationalRequired) {
            Toast.makeText(
                activity,
                rationaleMsg,
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                activity,
                "Permission denied multiple times!!!\nSo Please allow it from settings!!!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}