package com.menene.quickshare.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.menene.quickshare.R
import com.menene.quickshare.presentantion.common.GenericTextFieldComposable
import com.menene.quickshare.presentantion.common.HorizontalLine
import com.menene.quickshare.presentantion.common.TextFieldComposable
import com.menene.quickshare.presentantion.common.WelcomeHeader
import com.menene.quickshare.presentantion.common.appGradient
import com.menene.quickshare.presentantion.common.buttons.ImageButtonStyle
import com.menene.quickshare.presentantion.common.buttons.WideButton
import com.menene.quickshare.ui.theme.Dimens

@Composable
fun RegisterScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appGradient())
            .padding(horizontal = Dimens.paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var usernameText = remember { mutableStateOf("") }
        var emailText = remember { mutableStateOf("") }
        var passwordText = remember { mutableStateOf("") }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            WelcomeHeader(stringResource(id = R.string.welcome_text_register))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TextFieldComposable(usernameText, stringResource(id = R.string.username))
            Text(
                text = "${usernameText.value.length}/20",
                color = if (usernameText.value.length > 20) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.End),
            )


            GenericTextFieldComposable(emailText, stringResource(id = R.string.email), false)
            GenericTextFieldComposable(passwordText, stringResource(id = R.string.password), true)
        }

        Column {
            WideButton(
                onClick = {},
                stringResource(id = R.string.create_account)
            )

            HorizontalLine(stringResource(id = R.string.or_sign_up))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.paddingMedium)
            ) {
                ImageButtonStyle(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.google_icon_logo
                )
                ImageButtonStyle(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.github_mark_white
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}