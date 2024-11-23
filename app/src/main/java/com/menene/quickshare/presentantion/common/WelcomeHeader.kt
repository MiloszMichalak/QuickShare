package com.menene.quickshare.presentantion.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.menene.quickshare.ui.theme.Dimens

@Composable
fun WelcomeHeader(text1: String) {
    Text(
        modifier = Modifier.padding(bottom = Dimens.paddingLarge),
        text = text1,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
}