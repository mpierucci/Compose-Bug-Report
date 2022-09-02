package com.mpierucci.composecrash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class NavFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        return requireContext().createComposableView {
            Button(
                onClick = { findNavController().navigate(R.id.action_navFragment_to_myFragment) }
            ) {
                Text(text = "Navigate to pager")
            }
        }
    }

}