package com.mpierucci.composecrash

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.mpierucci.composecrash.ui.theme.ComposeCrashTheme

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeCrashTheme {
                val state by viewModel.state.observeAsState()

                LazyRow {
                    item {
                        Render(state!!)
                    }
                }
            }
        }
    }


    @Composable
    fun Render(state: State) {
        when(state.pageOne) {
            false -> {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())) {

                    PageOneContent()
                    PageOneButtons(viewModel::updatePageOne)
                }
            }
            else -> {
                Box(modifier = Modifier.fillMaxSize()) {

                }
            }
        }
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
