package com.app.coins.custom.top_bar

import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarComponentUIModel(
    val title: String,
    val shouldShowBackIcon: Boolean,
    val endIcon: ImageVector? = null
)
