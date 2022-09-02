package com.mpierucci.composecrash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mpierucci.composecrash.pager.PageOne
import com.mpierucci.composecrash.pager.PageTwo
import com.mpierucci.composecrash.pager.Pager
import com.mpierucci.composecrash.ui.theme.ComposeCrashTheme
import com.mpierucci.composecrash.ui.theme.CustomScaffold
import com.mpierucci.composecrash.ui.theme.ScaffoldState

class MyFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return requireContext().createComposableView {
            val state by viewModel.state.observeAsState()
            CustomScaffold(
                scrollState = null,
                toolbarTitle = "Toolbar",
                state = ScaffoldState.Content {
                    Column(Modifier.fillMaxSize()) {
                        Pager(
                            state = state!!,
                            pages = listOf(PageOne(viewModel), PageTwo(viewModel))
                        )
                    }
                }
            )
        }
    }
}


fun Context.createComposableView(content: @Composable () -> Unit): ComposeView =
    ComposeView(this).apply { setContent { ComposeCrashTheme { content() } } }