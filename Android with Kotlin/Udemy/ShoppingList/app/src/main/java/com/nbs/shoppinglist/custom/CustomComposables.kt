package com.nbs.shoppinglist.custom

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomSpacer(width: Dp = 0.dp, height: Dp = 0.dp, modifier: Modifier = Modifier) {
    val isVertical = width == 0.dp
    if (isVertical)
        Spacer(modifier.height(height))
    else
        Spacer(modifier.width(width))
}

@Composable
fun ErrorText(msg: String = "Invalid Value") {
    Text(text = msg, color = MaterialTheme.colorScheme.error)
}



@Composable
fun RequestPermission(
    requestedPermissions: List<String>,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: (String) -> Unit
) {
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { grantedPermissions ->
            val allPermissionsGranted = requestedPermissions.all {
                grantedPermissions[it] == true
            }

            if (allPermissionsGranted) {
                onPermissionGranted()
            } else {
                val deniedPermission = grantedPermissions.entries.first { !it.value }
                onPermissionDenied(deniedPermission.key)
            }
        }
    )

    requestPermissionLauncher.launch(requestedPermissions.toTypedArray())
}
