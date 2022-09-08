package com.mpierucci.composecrash

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.mpierucci.composecrash.ui.theme.ComposeCrashTheme

@OptIn(ExperimentalPagerApi::class)
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeCrashTheme {
                HorizontalPager(count = 1) {
                    Render()
                }
            }
        }
    }

    @Composable
    fun Render() {
        val (mutate, onMutate) = remember { mutableStateOf(false) }
        when (mutate) {
            false -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                        .verticalScroll(rememberScrollState())
                ) {
                    OutlinedTextField(value = "Lorem", onValueChange = {})
                    Button(onClick = { onMutate(true) }) {
                        Text(text = "Mutate State")
                    }
                }
            }
            true -> {
                Box(modifier = Modifier.fillMaxSize()) {

                }
            }
        }
    }
}
