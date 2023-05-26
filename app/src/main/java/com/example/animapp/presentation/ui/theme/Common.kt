package com.example.animapp.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class MyColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val controlColor: Color,
    val errorColor: Color,
)

data class MyTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val caption: TextStyle,
    val globalTextStyle: TextStyle,
)

data class MyShape(
    val padding: Dp,
    val cornersStyle: Shape
)

object MyTheme {
    val colors: MyColors
        @Composable
        get() = LocalMyColors.current

    val typography: MyTypography
        @Composable
        get() = LocalMyTypography.current

    val shapes: MyShape
        @Composable
        get() = LocalMyShape.current
}

enum class MyStyle {
    Purple, Orange, Blue, Red, Green, Yellow
}

enum class MySize {
    Small, Medium, Big
}

enum class MyCorners {
    Flat, Rounded
}

val LocalMyColors = staticCompositionLocalOf<MyColors> {
    error("No colors provided")
}

val LocalMyTypography = staticCompositionLocalOf<MyTypography> {
    error("No font provided")
}

val LocalMyShape = staticCompositionLocalOf<MyShape> {
    error("No shapes provided")
}
