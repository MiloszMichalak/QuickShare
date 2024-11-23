package com.menene.quickshare.presentantion.common.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.menene.quickshare.ui.theme.Dimens

@Composable
fun WideButton(
    onClick: () -> Unit,
    text: String) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.paddingSmall),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.surfaceContainerLowest
        )
    }
}
