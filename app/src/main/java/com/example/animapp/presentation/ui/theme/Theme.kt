package com.example.animapp.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

@Composable
fun MyTheme(
    style: MyStyle = MyStyle.Purple,
    textSize: MySize = MySize.Medium,
    paddingSize: MySize = MySize.Medium,
    corners: MyCorners = MyCorners.Rounded,
    darkTheme: Boolean = isSystemInDarkTheme(),
    fontFamily: FontFamily = FontFamily.Default,
    content: @Composable () -> Unit
) {

    val colors = when {
        darkTheme -> {
            when (style) {
                MyStyle.Purple -> purpleDarkPalette
                MyStyle.Blue -> blueDarkPalette
                MyStyle.Orange -> orangeDarkPalette
                MyStyle.Red -> redDarkPalette
                MyStyle.Green -> greenDarkPalette
                MyStyle.Yellow -> yellowDarkPalette
            }
        }

        else -> {
            when (style) {
                MyStyle.Purple -> purpleLightPalette
                MyStyle.Blue -> blueLightPalette
                MyStyle.Orange -> orangeLightPalette
                MyStyle.Red -> redLightPalette
                MyStyle.Green -> greenLightPalette
                MyStyle.Yellow -> yellowLightPalette
            }
        }
    }

    val typography = MyTypography(
        heading = TextStyle(
            fontSize = when (textSize) {
                MySize.Small -> 24.sp
                MySize.Medium -> 28.sp
                MySize.Big -> 32.sp
            },
            fontWeight = FontWeight.Bold,
        ),

        body = TextStyle(
            fontSize = when (textSize) {
                MySize.Small -> 14.sp
                MySize.Medium -> 16.sp
                MySize.Big -> 18.sp
            },
            fontWeight = FontWeight.Normal,
        ),

        caption = TextStyle(
            fontSize = when (textSize) {
                MySize.Small -> 10.sp
                MySize.Medium -> 12.sp
                MySize.Big -> 14.sp
            },
        ),

        globalTextStyle = TextStyle(
            fontFamily = fontFamily,
            fontSize = when(fontFamily) {
                FontFamily.Default -> 12.sp
                FontFamily.Cursive -> 14.sp
                FontFamily.Monospace -> 12.sp
                else -> 25.sp
            }
        )
    )

    val shapes = MyShape(
        padding = when (paddingSize) {
            MySize.Small -> 12.dp
            MySize.Medium -> 16.dp
            MySize.Big -> 20.dp
        },
        cornersStyle = when (corners) {
            MyCorners.Flat -> RoundedCornerShape(0.dp)
            MyCorners.Rounded -> RoundedCornerShape(8.dp)
        }
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primaryBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalMyColors provides colors,
        LocalMyTypography provides typography,
        LocalMyShape provides shapes,
        content = content
    )
}
