package com.menene.quickshare.presentantion.common.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun ImageButtonStyle(onClick: () -> Unit, modifier: Modifier, @DrawableRes icon: Int) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Login button",
        )
    }
}