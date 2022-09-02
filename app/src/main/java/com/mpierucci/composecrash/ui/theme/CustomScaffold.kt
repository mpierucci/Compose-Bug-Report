package com.mpierucci.composecrash.ui.theme

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.annotation.RawRes
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.mpierucci.composecrash.R
import com.mpierucci.composecrash.loader.LoadingItem
import com.mpierucci.composecrash.toolbar.Toolbar
import java.util.concurrent.TimeUnit

@Composable
fun CustomScaffold(
    state: ScaffoldState,
    scrollState: ScrollState? = rememberScrollState(),
    toolbarTitle: String? = null,
    toolbarContent: @Composable (RowScope.() -> Unit)? = null,
) {
    Layout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        content = {
            Toolbar(
                state = state,
                scrollState = scrollState,
                toolbarTitle = toolbarTitle,
                toolbarContent = toolbarContent,
                onBackPressed = { },
                menuExpanded = mutableStateOf(false)
            )
            when (state) {
                is ScaffoldState.Content -> {
                    Content(scrollState = scrollState, content = state.content)
                }
                is ScaffoldState.Message -> {
                    Text(text = state.message)
                }
                ScaffoldState.Loading -> Loading(Color.Black)
            }
            Buttons(state)
        }

    ) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            val (toolbarMeasurable, contentMeasurable, buttonsMeasurable) = measurables
            val toolbarPlaceable =
                toolbarMeasurable.measure(constraints.copy(minWidth = 0, minHeight = 0))

            val buttonsPlaceable =
                buttonsMeasurable.measure(constraints.copy(minWidth = 0, minHeight = 0))

            val contentHeight =
                constraints.maxHeight - toolbarPlaceable.height - buttonsPlaceable.height
            val contentPlaceable = contentMeasurable.measure(
                constraints.copy(minWidth = 0, minHeight = 0, maxHeight = contentHeight)
            )

            toolbarPlaceable.place(x = 0, y = 0)
            contentPlaceable.place(x = 0, y = toolbarPlaceable.height)
            buttonsPlaceable.place(x = 0, y = constraints.maxHeight - buttonsPlaceable.height)
        }
    }
}

sealed class ScaffoldState {
    data class Content(
        val buttons: (@Composable ColumnScope.() -> Unit)? = null,
        val content: @Composable BoxScope.() -> Unit
    ) : ScaffoldState()

    data class Message(
        val message: String,
        val buttons: (@Composable ColumnScope.() -> Unit)? = null,
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
            is ScaffoldState.Message -> state.buttons
            else -> null
        }

    if (buttons != null) {
        Surface(elevation = 4.dp) {
            Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {
                DividerItem()
                Column(modifier = Modifier.padding(vertical = 16.dp)) { buttons() }
            }
        }
    } else Box {}
}

@Composable
fun DividerItem(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
    )
}

@Composable
private fun Loading(backgroundColor: Color) {
    LoadingItem(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    )
}
