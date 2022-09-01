package com.mpierucci.composecrash.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pager(
    updated: Boolean?,
    onUpdateClick: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState()
        ScrollableTabRow(
            selectedTabIndex = 0,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            Tab(
                selected = pagerState.currentPage == 0,
                text = {
                    Text(text = "This is a tab")
                },
                enabled = true,
                onClick = {}
            )
        }
        HorizontalPager(
            count = 1,
            state = pagerState,
            userScrollEnabled = false,
            content = { page: Int ->
                CustomScaffold(
                    state = when (updated) {
                        null -> ScaffoldState.Message("Showing a message")
                        false -> ScaffoldState.Content(content = { PagerContent(onUpdateClick = onUpdateClick) })
                        true -> ScaffoldState.Content(content = { PagerContent2(onUpdateClick = onUpdateClick) })
                    }
                )
            }
        )
    }
}

@Composable
private fun PagerContent(
    onUpdateClick: () -> Unit
) {
    Column {
        Text(text = "Displaying Pager Content", color = MaterialTheme.colors.onBackground)
        Button(onClick = onUpdateClick) {
            Text(text = "Change to pager 2")
        }
    }
}

@Composable
private fun PagerContent2(
    onUpdateClick: () -> Unit
) {
    Column {
        Text(text = "Displaying Pager Content2", color = MaterialTheme.colors.onBackground)
        Button(onClick = onUpdateClick) {
            Text(text = "Change to message")
        }
    }
}