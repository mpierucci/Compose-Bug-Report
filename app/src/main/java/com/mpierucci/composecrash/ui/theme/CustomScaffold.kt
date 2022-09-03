package com.mpierucci.composecrash.ui.theme

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.mpierucci.composecrash.loader.LoadingItem

@Composable
fun CustomScaffold(
    state: ScaffoldState,
    scrollState: ScrollState? = rememberScrollState()
) {
    Layout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        content = {
            when (state) {
                is ScaffoldState.Content -> {
                    Content(scrollState = scrollState, content = state.content)
                }
                ScaffoldState.Loading -> Loading(Color.Black)
            }
            Buttons(state)
        }

    ) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            val (contentMeasurable, buttonsMeasurable) = measurables

            val buttonsPlaceable =
                buttonsMeasurable.measure(constraints.copy(minWidth = 0, minHeight = 0))

            val contentHeight =
                constraints.maxHeight - buttonsPlaceable.height
            val contentPlaceable = contentMeasurable.measure(
                constraints.copy(minWidth = 0, minHeight = 0, maxHeight = contentHeight)
            )

            contentPlaceable.place(x = 0, y = 0)
            buttonsPlaceable.place(x = 0, y = constraints.maxHeight - buttonsPlaceable.height)
        }
    }
}

sealed class ScaffoldState {
    data class Content(
        val buttons: (@Composable ColumnScope.() -> Unit)? = null,
        val content: @Composable BoxScope.() -> Unit
    ) : ScaffoldState()

    object Loading : ScaffoldState()
}


@Composable
private fun Content(
    scrollState: ScrollState?,
    content: @Composable BoxScope.() -> Unit
) {
    Layout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        content = {
            Box(
                Modifier
                    .run { if (scrollState != null) verticalScroll(scrollState) else this }
            ) {
                content()

            }
        }) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            measurables[0].measure(constraints).place(x = 0, y = 0)
        }
    }
}

@Composable
private fun Buttons(state: ScaffoldState) {
    val buttons: @Composable (ColumnScope.() -> Unit)? =
        when (state) {
            is ScaffoldState.Content -> state.buttons
            else -> null
        }

    if (buttons != null) {
        Surface(elevation = 4.dp) {
            Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {
                Column(modifier = Modifier.padding(vertical = 16.dp)) { buttons() }
            }
        }
    } else Box {}
}

@Composable
private fun Loading(backgroundColor: Color) {
    LoadingItem(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    )
}
