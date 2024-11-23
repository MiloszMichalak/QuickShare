package com.menene.quickshare.presentantion.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.menene.quickshare.presentantion.Page
import com.menene.quickshare.presentantion.pages
import com.menene.quickshare.ui.theme.Dimens

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentScale = ContentScale.Crop,
            contentDescription = "onboarding Image"
        )
        Spacer(modifier = Modifier.padding(Dimens.paddingLarge))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = Dimens.paddingLarge),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = Dimens.paddingLarge),
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainPreview(){
    OnBoardingPage(
        page = pages[0]
    )
}