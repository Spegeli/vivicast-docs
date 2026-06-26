package com.vivicast.tv.core.designsystem

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

object VivicastFocus {
    val RingWidth = 2.dp
    val RingGap = 3.dp
    val GlowElevation = 18.dp

    const val ScaleSmall = 1.02f
    const val ScaleCard = 1.06f
    const val ScaleButton = 1.03f

    const val FocusAnimationMillis = 120
}

/**
 * Generic TV focus modifier for cards, rows and buttons.
 *
 * The exact visual may be refined during implementation,
 * but focus must always be visible through color + shape/scale.
 */
@Composable
fun Modifier.vivicastFocusableSurface(
    focused: Boolean,
    shape: Shape,
    scaleWhenFocused: Float = VivicastFocus.ScaleSmall,
): Modifier {
    val scale by animateFloatAsState(
        targetValue = if (focused) scaleWhenFocused else 1f,
        animationSpec = tween(VivicastFocus.FocusAnimationMillis),
        label = "vivicast-focus-scale"
    )

    return this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .shadow(
            elevation = if (focused) VivicastFocus.GlowElevation else 2.dp,
            shape = shape,
            clip = false
        )
        .border(
            border = BorderStroke(
                width = if (focused) VivicastFocus.RingWidth else 1.dp,
                color = if (focused) VivicastColors.FocusRing else VivicastColors.BorderSoft
            ),
            shape = shape
        )
}
