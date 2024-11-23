package com.menene.quickshare.presentantion.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.menene.quickshare.ui.theme.Dimens

@Composable
fun HorizontalLine(text: String) {
    Row(
        modifier = Modifier.padding(bottom = Dimens.paddingLarge, top = Dimens.paddingExtraLarge),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(MaterialTheme.colorScheme.onSurface)
                .weight(1f)
        )
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = Dimens.paddingSmall),
            color = MaterialTheme.colorScheme.onSurface
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(MaterialTheme.colorScheme.onSurface)
                .weight(1f)
        )
    }
}
