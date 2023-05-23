package com.example.animapp.presentation.ui.utils

import androidx.compose.ui.text.font.FontFamily
import com.example.animapp.presentation.ui.theme.MyCorners
import com.example.animapp.presentation.ui.theme.MySize
import com.example.animapp.presentation.ui.theme.MyStyle

data class CurrentSettings(
    val isDarkMode: Boolean,
    val textSize: MySize,
    val paddingSize: MySize,
    val cornerStyle: MyCorners,
    val style: MyStyle,
    val fontFamily: FontFamily,
)
