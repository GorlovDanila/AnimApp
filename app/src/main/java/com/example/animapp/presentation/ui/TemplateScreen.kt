package com.example.animapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.animapp.R
import com.example.animapp.presentation.ui.theme.MyTheme

@Composable
fun TemplateScreen() {
    TemplateContent()
}

@Composable
fun TemplateContent() {
    Image(
        painter = painterResource(id = R.drawable.mem),
        contentDescription = null,
        modifier = Modifier.fillMaxSize().background(MyTheme.colors.primaryBackground),
    )
}
