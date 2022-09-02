package com.mpierucci.composecrash.loader

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.mpierucci.composecrash.R
import java.util.concurrent.TimeUnit

@Composable
fun LottieLoader(
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
        ) {
            LottieView(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun LottieView(
    modifier: Modifier = Modifier,
) {
    AndroidView(
        modifier = modifier,
        factory = {
            LottieAnimationView(it).apply {
                alpha = 0f
                repeatCount = LottieDrawable.INFINITE
                setAnimation(R.raw.constantine_050_circle)
                playAnimation()
                animate()
                    .setDuration(TimeUnit.SECONDS.toMillis(1))
                    .alpha(1f)
            }
        }
    )
}