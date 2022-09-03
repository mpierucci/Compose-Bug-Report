package com.mpierucci.composecrash

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.view.WindowCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.mpierucci.composecrash.ui.theme.ComposeCrashTheme
import com.mpierucci.composecrash.ui.theme.CustomScaffold
import com.mpierucci.composecrash.ui.theme.ScaffoldState

@OptIn(ExperimentalPagerApi::class)
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeCrashTheme {
                val state by viewModel.state.observeAsState()

                HorizontalPager(
                    count = 1,
                    state = rememberPagerState()
                ) {
                    Render(state!!)
                }
            }
        }
    }


    @Composable
    fun Render(state: State) {
        CustomScaffold(
            state = when (state.pageOne) {
                false -> ScaffoldState.Content(
                    buttons = { PageOneButtons(viewModel::updatePageOne) },
                    content = { PageOneContent() }
                )
                else -> ScaffoldState.Loading
            }
        )
    }


    @Composable
    private fun PageOneContent() {
        Column {
            Text(text = "Lorem Impsum", color = MaterialTheme.colors.onBackground)
            OutlinedTextField(value = "Lorem", onValueChange = {})
        }
    }


    @Composable
    private fun PageOneButtons(onUpdateClick: () -> Unit) {
        Button(onClick = onUpdateClick) {
            Text(text = "Mutate PageOne State")
        }
    }
}
