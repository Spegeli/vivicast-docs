// TEMPLATE ONLY.
// Copy this file into the app repository and set the real package there.
// This file is not app code inside vivicast-docs and does not define final module structure.

import androidx.compose.ui.graphics.Color

/**
 * Vivicast color tokens.
 *
 * Primary visual source:
 * - current high-fidelity Vivicast mockups
 *
 * Secondary source:
 * - original repo design tokens, only where the new mockups do not define a value clearly
 */
object VivicastColors {
    val Background = Color(0xFF050914)
    val BackgroundDeep = Color(0xFF020617)
    val BackgroundElevated = Color(0xFF07111F)

    val Surface = Color(0xFF0B1628)
    val SurfaceHigh = Color(0xFF101D33)
    val SurfaceFocus = Color(0xFF102B46)
    val SurfacePressed = Color(0xFF15395C)
    val SurfaceDisabled = Color(0x80111827)

    val Panel = Color(0xCC07111F)
    val PanelStrong = Color(0xE60B1628)
    val Dialog = Color(0xF20B1628)

    val Border = Color(0xFF22334A)
    val BorderSoft = Color(0xFF17263A)

    val TextPrimary = Color(0xFFF8FAFC)
    val TextSecondary = Color(0xFFCBD5E1)
    val TextTertiary = Color(0xFF94A3B8)
    val TextDisabled = Color(0xFF64748B)
    val TextOnAccent = Color(0xFF031525)

    val Accent = Color(0xFF00C8FF)
    val AccentStrong = Color(0xFF38D5FF)
    val FocusRing = Color(0xFF00D4FF)
    val FocusGlow = Color(0x6600BFFF)
    val Progress = Color(0xFF159BFF)

    val Live = Color(0xFFFF2D3A)
    val Favorite = Color(0xFFFACC15)
    val Success = Color(0xFF22C55E)
    val Warning = Color(0xFFF59E0B)
    val Error = Color(0xFFEF4444)
    val CatchUp = Color(0xFF0EA5E9)
}
