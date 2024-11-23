package com.menene.quickshare.presentantion.onboarding

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityOptionsCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.menene.quickshare.navigation.NavigationActivity
import com.menene.quickshare.presentantion.common.buttons.NoteButton
import com.menene.quickshare.presentantion.common.buttons.NoteTextButton
import com.menene.quickshare.presentantion.pages
import com.menene.quickshare.ui.theme.Dimens
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0)
        val context = LocalContext.current

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Finish")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            count = pages.size
        ) { index ->
            OnBoardingPage(
                page = pages[index]
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.paddingLarge)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            PageIndicator(numberOfPages = pages.size, currentPage = pagerState.currentPage)

            Spacer(modifier = Modifier.weight(1f))

            val scope = rememberCoroutineScope()

            if (buttonState.value[0].isNotEmpty()) {
                NoteTextButton(
                    text = buttonState.value[0],
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    }
                )
            }

            NoteButton(
                text = buttonState.value[1],
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            val intent = Intent(context, NavigationActivity::class.java)
                            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity).toBundle()
                            context.startActivity(intent, options)
                        } else {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    OnBoardingScreen()
}
