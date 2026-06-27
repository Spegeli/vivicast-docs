// TEMPLATE ONLY.
// Copy this file into the app repository and set the real package there.
// This file is not app code inside vivicast-docs and does not define final module structure.

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

/**
 * Minimal theme shell for Vivicast.
 *
 * In a real app this can wrap MaterialTheme / TV Material3.
 * Keep Vivicast tokens centralized and do not duplicate values per screen.
 */
@Composable
fun VivicastTheme(
    content: @Composable () -> Unit
) {
    content()
}

@Composable
fun VivicastBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.background(
            brush = Brush.radialGradient(
                colors = listOf(
                    VivicastColors.BackgroundElevated,
                    VivicastColors.Background,
                    VivicastColors.BackgroundDeep
                ),
                center = Offset(960f, 420f),
                radius = 1400f
            )
        )
    ) {
        content()
    }
}
