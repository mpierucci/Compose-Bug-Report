package com.mpierucci.composecrash

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.view.WindowCompat
import com.mpierucci.composecrash.pager.PageOne
import com.mpierucci.composecrash.pager.PageTwo
import com.mpierucci.composecrash.pager.Pager
import com.mpierucci.composecrash.ui.theme.ComposeCrashTheme

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeCrashTheme {
                val state by viewModel.state.observeAsState()
                Pager(
                    state = state!!,
                    pages = listOf(PageOne(viewModel), PageTwo(viewModel))
                )
            }
        }
    }
}
