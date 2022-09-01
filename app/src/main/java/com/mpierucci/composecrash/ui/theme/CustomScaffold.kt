package com.mpierucci.composecrash.ui.theme

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomScaffold(
    state: ScaffoldState,
    scrollState: ScrollState? = rememberScrollState(),
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        when (state) {
            is ScaffoldState.Content -> {
                Content(scrollState = scrollState, content = state.content)
            }
            is ScaffoldState.Message -> {
                Text(text = state.message)
            }
        }
    }
}

sealed class ScaffoldState {
    data class Content(val content: @Composable BoxScope.() -> Unit) : ScaffoldState()
    data class Message(val message: String) : ScaffoldState()
}


@Composable
private fun Content(
    scrollState: ScrollState?,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .run { if (scrollState != null) verticalScroll(scrollState) else this }
    ) { content() }
}
