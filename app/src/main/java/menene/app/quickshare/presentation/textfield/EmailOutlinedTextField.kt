package menene.app.quickshare.presentation.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import menene.app.quickshare.R
import menene.app.quickshare.utility.Validation

@Composable
fun EmailOutlinedTextField(
    value: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 16.dp),
        value = value,
        isError = isError,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ),
        label = { Text(stringResource(id = R.string.email)) },
        leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "email") },
        singleLine = true,
        shape = RoundedCornerShape(50),
    )
}