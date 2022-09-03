package com.mpierucci.composecrash.pager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.mpierucci.composecrash.State

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pager(
    state: State,
    renderPage: Page
) {
    Column(Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState()
        HorizontalPager(
            count = 1,
            state = pagerState,
            userScrollEnabled = true,
            content = { page: Int ->
                renderPage.Render(state)
            }
        )
    }
}
