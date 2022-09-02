package com.mpierucci.composecrash.pager

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.mpierucci.composecrash.MainViewModel
import com.mpierucci.composecrash.State
import com.mpierucci.composecrash.ui.theme.CustomScaffold
import com.mpierucci.composecrash.ui.theme.ScaffoldState

interface Page {

    @Composable
    fun Render(state: State)
}


class PageOne(
    private val viewModel: MainViewModel
) : Page {

    @Composable
    override fun Render(state: State) {
        CustomScaffold(
            state = when (state.pageOne) {
                false -> ScaffoldState.Content(
                    buttons = { PageOneButtons(viewModel::updatePageOne) },
                    content = { PageOneContent() }
                )
                true -> ScaffoldState.Loading
                null -> ScaffoldState.Content(content = { DefaultPageContent(viewModel::updatePageOne) })
            }
        )
    }


    @Composable
    private fun PageOneContent() {
        Column {
            Text(text = "Showing page one content", color = MaterialTheme.colors.onBackground)
            Text(text = "Nothing special", color = MaterialTheme.colors.onBackground)
            OutlinedTextField(value = "asdasdsd", onValueChange = {} )
        }
    }


    @Composable
    private fun PageOneButtons(onUpdateClick: () -> Unit) {
        Button(onClick = onUpdateClick) {
            Text(text = "Mutate PageOne State")
        }
    }
}

class PageTwo(
    private val viewModel: MainViewModel
) : Page {

    @Composable
    override fun Render(state: State) {
        CustomScaffold(
            state = when (state.pageTwo) {
                false -> ScaffoldState.Content(
                    buttons = { PageTwoButtons(viewModel::updatePageTwo) },
                    content = { PageTwoContent() }
                )
                true -> ScaffoldState.Loading
                null -> ScaffoldState.Content(content = { DefaultPageContent(viewModel::updatePageTwo) })
            }
        )
    }


    @Composable
    private fun PageTwoContent() {
        Column {
            Text(text = "Showing page two content", color = MaterialTheme.colors.onBackground)
            Text(text = "Nothing special", color = MaterialTheme.colors.onBackground)
        }
    }


    @Composable
    private fun PageTwoButtons(onUpdateClick: () -> Unit) {
        Button(onClick = onUpdateClick) {
            Text(text = "Mutate PageTwo State")
        }
    }
}

@Composable
private fun DefaultPageContent(
    onButtonClick: () -> Unit
) {
    Column {
        Text(text = "Displaying Some Default content", color = MaterialTheme.colors.onBackground)
        Button(onClick = onButtonClick) {
            Text(text = "No-op")
        }
    }
}