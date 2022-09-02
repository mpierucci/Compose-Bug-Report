package com.mpierucci.composecrash.toolbar

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mpierucci.composecrash.ui.theme.ScaffoldState

@Composable
fun Toolbar(
    state: ScaffoldState,
    scrollState: ScrollState?,
    toolbarTitle: String?,
    toolbarContent: @Composable (RowScope.() -> Unit)?,
    onBackPressed: (() -> Unit)?,
    menuExpanded: MutableState<Boolean>? = null
) {
    if (toolbarTitle == null && toolbarContent == null) {
        Box {}
        return
    }

    val toolbarElevation by updateTransition(targetState = scrollState, label = null)
        .animateDp(label = "toolbar") {
            if ((it == null || it.value == 0 && state is ScaffoldState.Content) || state is ScaffoldState.Loading
            ) 0.dp else AppBarDefaults.TopAppBarElevation
        }

    val navigationIcon = @Composable {
        if (onBackPressed != null) IconButton(onClick = onBackPressed) {
            Box {

            }
        }
    }

    TopAppBar(
        elevation = toolbarElevation,
        title = {
            Row(modifier = Modifier) {
                toolbarContent?.invoke(this)
                toolbarTitle?.let {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = toolbarTitle,
                    )
                }
            }
        },
        navigationIcon = if (onBackPressed == null) null else navigationIcon,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        actions = {
            if (menuExpanded != null) IconButton(
                onClick = { menuExpanded.value = !menuExpanded.value }
            ) {
                Box {

                }
            }
        }
    )
}