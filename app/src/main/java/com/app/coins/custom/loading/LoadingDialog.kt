package com.app.coins.custom.loading

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.coins.utils.theme.light

@Composable
fun LoadingDialog(
    dismissOnBackPress: Boolean = false,
    dismissOnClickOutside: Boolean = false
) {
    Dialog(
        onDismissRequest = { },
        DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside
        )
    ) {
        CircularProgressIndicator(color = light)

    }
}