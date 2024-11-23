package com.menene.quickshare.presentantion.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TextFieldComposable(text: MutableState<String>, label: String) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(
            Color.Transparent
        ),
        value = text.value,
        onValueChange = ({text.value = it}),
        label = { Text(label) },
        singleLine = true,
        shape = MaterialTheme.shapes.medium
    )
}