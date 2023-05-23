package com.example.animapp.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animapp.presentation.ui.theme.*
import com.example.animapp.presentation.ui.utils.Click
import com.example.animapp.presentation.ui.utils.LocalSettingsEventBus

@Composable
fun SettingsScreen(
) {
    val settingsEventBus = LocalSettingsEventBus.current
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    Surface(color = MyTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier.padding(MyTheme.shapes.padding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Dark Theme",
                    color = MyTheme.colors.primaryText,
                    style = MyTheme.typography.globalTextStyle,
                )
                Checkbox(
                    checked = currentSettings.isDarkMode, onCheckedChange = {
                        settingsEventBus.updateDarkMode(!currentSettings.isDarkMode)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MyTheme.colors.tintColor,
                        uncheckedColor = MyTheme.colors.secondaryText
                    )
                )
            }

            Divider(
                modifier = Modifier.padding(start = MyTheme.shapes.padding),
                thickness = 0.5.dp,
                color = MyTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                backgroundColor = MyTheme.colors.secondaryBackground,
                shape = MyTheme.shapes.cornersStyle
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tint color",
                        color = MyTheme.colors.primaryText,
                        style = MyTheme.typography.globalTextStyle
                    )

                    Row(
                        modifier = Modifier
                            .padding(MyTheme.shapes.padding)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ColorCard(
                            color =
                            if (currentSettings.isDarkMode)
                                purpleDarkPalette.tintColor
                            else purpleLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(MyStyle.Purple)
                            }
                        )
                        ColorCard(
                            color =
                            if (currentSettings.isDarkMode)
                                orangeDarkPalette.tintColor
                            else orangeLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(MyStyle.Orange)
                            }
                        )
                        ColorCard(
                            color =
                            if (currentSettings.isDarkMode)
                                blueDarkPalette.tintColor
                            else blueLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(MyStyle.Blue)
                            }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(MyTheme.shapes.padding)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ColorCard(
                            color =
                            if (currentSettings.isDarkMode)
                                redDarkPalette.tintColor
                            else redLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(MyStyle.Red)
                            }
                        )
                        ColorCard(
                            color =
                            if (currentSettings.isDarkMode)
                                greenDarkPalette.tintColor
                            else greenLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(MyStyle.Green)
                            }
                        )
                        ColorCard(
                            color =
                            if (currentSettings.isDarkMode)
                                yellowDarkPalette.tintColor
                            else yellowLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(MyStyle.Yellow)
                            }
                        )
                    }
                }
            }

            Divider(
                modifier = Modifier.padding(start = MyTheme.shapes.padding),
                thickness = 0.5.dp,
                color = MyTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                backgroundColor = MyTheme.colors.secondaryBackground,
                shape = MyTheme.shapes.cornersStyle
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Font family",
                        color = MyTheme.colors.primaryText,
                        style = MyTheme.typography.globalTextStyle
                    )
                    Column(
                        modifier = Modifier
                            .padding(MyTheme.shapes.padding)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        FontFamilyCard(fontFamily = FontFamily.Default, onClick = {
                            settingsEventBus.updateFontFamily(FontFamily.Default)
                        })
                        FontFamilyCard(fontFamily = FontFamily.Monospace, onClick = {
                            settingsEventBus.updateFontFamily(FontFamily.Monospace)
                        })
                        FontFamilyCard(fontFamily = FontFamily.Cursive, onClick = {
                            settingsEventBus.updateFontFamily(FontFamily.Cursive)
                        })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColorCard(color: Color, onClick: Click) {
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .size(56.dp, 56.dp),
        backgroundColor = color,
        shape = MyTheme.shapes.cornersStyle,
        elevation = 3.dp,
    ) {}
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun FontFamilyCard(fontFamily: FontFamily, onClick: Click) {
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = MyTheme.colors.controlColor,
        shape = MyTheme.shapes.cornersStyle,
        elevation = 3.dp,
    ) {
        Text(
            text = fontFamily.toString().replace("FontFamily.", ""),
            fontSize = 16.sp,
            fontFamily = fontFamily,
            color = MyTheme.colors.primaryText,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}
