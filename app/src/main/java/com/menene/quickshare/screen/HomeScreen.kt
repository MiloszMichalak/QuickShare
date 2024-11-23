package com.menene.quickshare.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.menene.quickshare.R
import com.menene.quickshare.navigation.Screen
import com.menene.quickshare.presentantion.common.appGradient
import com.menene.quickshare.presentantion.common.buttons.WideButton
import com.menene.quickshare.presentantion.common.buttons.WideTransparentButton
import com.menene.quickshare.ui.theme.Dimens

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(appGradient())
            .padding(horizontal = Dimens.paddingMedium)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Create",
        )

        Text(
            modifier = Modifier.padding(top = Dimens.paddingMedium, bottom = Dimens.paddingLarge),
            text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall
        )

        WideButton(
            text = "Login",
            onClick = {
                navController.navigate(Screen.Login.route)
            }
        )
        WideTransparentButton(
            text = "Sign up",
            onClick = {
                navController.navigate(Screen.Register.route)
            }
        )
    }
}