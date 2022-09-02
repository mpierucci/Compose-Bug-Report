package com.mpierucci.composecrash

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(contentView())
        keepScreenOn()
    }


    private fun contentView(): View =
        FragmentContainerView(this).also { view ->
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            view.id = View.generateViewId()
            NavHostFragment.create(R.navigation.nav_grap).also { host ->
                supportFragmentManager.commit {
                    replace(view.id, host)
                    setReorderingAllowed(true)
                    setPrimaryNavigationFragment(host)
                }
            }
        }


    fun Activity.keepScreenOn(enable: Boolean = true) {
        if (enable) window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        else window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}
