package com.mpierucci.composecrash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mpierucci.composecrash.ui.theme.ComposeCrashTheme
import com.mpierucci.composecrash.ui.theme.CustomScaffold
import com.mpierucci.composecrash.ui.theme.Pager
import com.mpierucci.composecrash.ui.theme.ScaffoldState

class MyFragment : Fragment() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return requireContext().createComposableView {
            val updated by viewModel.state.observeAsState()
            CustomScaffold(
                scrollState = null,
                state = ScaffoldState.Content {
                    Pager(updated = updated, onUpdateClick = viewModel::update)
                }
            )
        }
    }
}


private fun Context.createComposableView(content: @Composable () -> Unit): ComposeView =
    ComposeView(this).apply { setContent { ComposeCrashTheme { content() } } }