package com.menene.quickshare.presentantion.common.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.menene.quickshare.ui.theme.Dimens

@Composable
fun WideTransparentButton(
    onClick: () -> Unit,
    text: String) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.paddingSmall),
            onClick = onClick,
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
            border = BorderStroke(1.dp, Color.White)
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onSurface)
        }
}