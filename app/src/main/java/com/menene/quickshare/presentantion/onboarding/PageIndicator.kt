package com.menene.quickshare.presentantion.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.menene.quickshare.ui.theme.Dimens


@Composable
fun PageIndicator(
    numberOfPages: Int,
    currentPage: Int,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = Color.Gray
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.paddingSmall),
        modifier = Modifier.padding(Dimens.paddingSmall)
    ) {
        repeat(numberOfPages) { page ->
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(color = if (page == currentPage) activeColor else inactiveColor)
            )
        }
    }
}
