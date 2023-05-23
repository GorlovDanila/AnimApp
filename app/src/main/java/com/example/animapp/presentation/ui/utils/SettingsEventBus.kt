package com.example.animapp.presentation.ui.utils

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.FontFamily
import com.example.animapp.presentation.ui.theme.MyCorners
import com.example.animapp.presentation.ui.theme.MySize
import com.example.animapp.presentation.ui.theme.MyStyle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsEventBus {
    private val _currentSettings: MutableStateFlow<CurrentSettings> = MutableStateFlow(
        CurrentSettings(
            isDarkMode = true,
            cornerStyle = MyCorners.Rounded,
            style = MyStyle.Orange,
            textSize = MySize.Medium,
            paddingSize = MySize.Medium,
            fontFamily = FontFamily.Default
        )
    )
    val currentSettings: StateFlow<CurrentSettings> = _currentSettings

    fun updateDarkMode(isDarkMode: Boolean) {
        _currentSettings.value = _currentSettings.value.copy(isDarkMode = isDarkMode)
    }

    fun updateStyle(style: MyStyle) {
        _currentSettings.value = _currentSettings.value.copy(style = style)
    }

    fun updateFontFamily(fontFamily: FontFamily) {
        _currentSettings.value = _currentSettings.value.copy(fontFamily = fontFamily)
    }
}

val LocalSettingsEventBus = staticCompositionLocalOf {
    SettingsEventBus()
}
