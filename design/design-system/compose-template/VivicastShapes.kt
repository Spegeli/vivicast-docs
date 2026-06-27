// TEMPLATE ONLY.
// Copy this file into the app repository and set the real package there.
// This file is not app code inside vivicast-docs and does not define final module structure.

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

object VivicastShapes {
    val Small = RoundedCornerShape(8.dp)
    val Medium = RoundedCornerShape(12.dp)
    val Large = RoundedCornerShape(16.dp)
    val XLarge = RoundedCornerShape(24.dp)
    val Pill = RoundedCornerShape(999.dp)

    val Panel = XLarge
    val Card = Large
    val SettingsRow = Large
    val Dialog = XLarge
    val Button = Medium
    val TopNavItem = Large
}
